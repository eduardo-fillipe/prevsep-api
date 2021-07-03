package br.ufs.hu.prevsep.web.api.config.security.filter;

import br.ufs.hu.prevsep.web.api.config.security.exception.BadRequestException;
import br.ufs.hu.prevsep.web.api.config.security.exception.ContentTypeNotAllowedException;
import br.ufs.hu.prevsep.web.api.config.security.exception.MethodNotAllowedException;
import br.ufs.hu.prevsep.web.api.dto.fault.ErrorDetailDTO;
import br.ufs.hu.prevsep.web.api.dto.fault.FaultDTO;
import br.ufs.hu.prevsep.web.api.dto.security.UsuarioLoginLogCreateDTO;
import br.ufs.hu.prevsep.web.api.dto.security.UsuarioLoginLogCreateDTO.UsuarioLoginLogCreateDTOBuilder;
import br.ufs.hu.prevsep.web.api.dto.security.UsuarioLoginLogDTO;
import br.ufs.hu.prevsep.web.api.dto.security.authentication.AccessTokenResponse;
import br.ufs.hu.prevsep.web.api.dto.security.authorization.role.RoleDTO;
import br.ufs.hu.prevsep.web.api.service.security.UsuarioLogService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OAuth2JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static final ObjectMapper mapper = new ObjectMapper();
    private final AuthenticationManager authenticationManager;
    private final long expirationTime;
    private final boolean forceLogLogin;
    private final String secret;
    private static final String GRANT_TYPE = "grant_type";
    private static final String CLIENT_CREDENTIALS = "client_credentials";
    private final UsuarioLogService usuarioLogService;

    public OAuth2JWTAuthenticationFilter(AuthenticationManager authenticationManager, String tokenUrl, long expirationTime, boolean forceLogLogin, String secret, UsuarioLogService usuarioLogService) {
        this.authenticationManager = authenticationManager;
        this.forceLogLogin = forceLogLogin;
        this.usuarioLogService = usuarioLogService;
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);
        this.expirationTime = expirationTime;
        this.secret = secret;
        setFilterProcessesUrl(tokenUrl);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        if (!req.getMethod().equals(HttpMethod.POST.toString()))
            throw new MethodNotAllowedException("Method not supported on authentication: " + req.getMethod());

        String contentType = req.getContentType();
        if (!MediaType.APPLICATION_FORM_URLENCODED_VALUE.equals(contentType))
            throw new ContentTypeNotAllowedException("Content-Type not supported on authentication: " + contentType);

        String headerAuthorization = req.getHeader(HttpHeaders.AUTHORIZATION);

        try {
            String body = new String(req.getInputStream().readAllBytes());
            Map<String, String> bodyParams = getParametersFromUrlEncoding(body);

            String grantType = bodyParams.get(GRANT_TYPE);
            if (CLIENT_CREDENTIALS.equals(grantType)) {
                Authentication authentication = getAuthenticationFromAuthorizationHeader(headerAuthorization);
                if (authentication == null)
                    throw new BadRequestException("Cannot extract the client credentials from header.");

                return authenticationManager.authenticate(authentication);
            }

            throw new BadRequestException("Unsupported grant_type.");

        } catch (IOException ex) {
            throw new InternalAuthenticationServiceException("Cannot extract the grant_type from request body.");
        }
    }

    private Map<String, String> getParametersFromUrlEncoding(String urlEncoding) {
        HashMap<String, String> result = new HashMap<>();

        if (urlEncoding != null && !urlEncoding.isEmpty()) {
            String[] parameters = urlEncoding.split("&");
            for (String parameter : parameters) {
                String[] splitParam = parameter.split("=");
                if (splitParam.length == 2) {
                    result.put(splitParam[0].toLowerCase(), URLDecoder.decode(splitParam[1], StandardCharsets.UTF_8));
                } else {
                    result.put(splitParam[0], null);
                }
            }
        }

        return result;
    }

    private Authentication getAuthenticationFromAuthorizationHeader(String header) {
        try {
            String[] typeValue = header.split(" ");

            if (typeValue.length == 2) {
                if (typeValue[0].equals("Basic")) {
                    String valueDecoded = new String(Base64.getDecoder().decode(typeValue[1]));
                    String username = valueDecoded.substring(0, valueDecoded.indexOf(':'));
                    String password = valueDecoded.substring(valueDecoded.indexOf(':') + 1);

                    return new UsernamePasswordAuthenticationToken(
                            username,
                            password,
                            new ArrayList<>());
                }
                return null;
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    private FaultDTO getFault(AuthenticationException ex) {
        FaultDTO fault = new FaultDTO();
        fault.setMessage("Authentication Failure.");
        fault.setError(ex.getClass().getSimpleName());
        ErrorDetailDTO errorDetailDTO = new ErrorDetailDTO();
        fault.setErrorDetail(errorDetailDTO);

        if (ex.getCause() != null) {
            errorDetailDTO.setExceptionClass(ex.getCause().getClass().getName());
            errorDetailDTO.setErrorDetail(ex.getCause().getMessage());
        } else {
            errorDetailDTO.setExceptionClass(ex.getClass().getName());
            errorDetailDTO.setErrorDetail(ex.getMessage());
        }

        if (ex instanceof BadCredentialsException) {
            fault.setStatusCode(HttpServletResponse.SC_UNAUTHORIZED);
            return fault;
        }

        if (ex instanceof BadRequestException || ex instanceof ContentTypeNotAllowedException) {
            fault.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
            return fault;
        }

        if (ex instanceof MethodNotAllowedException) {
            fault.setStatusCode(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            return fault;
        }

        if (ex instanceof InternalAuthenticationServiceException) {
            fault.setStatusCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return fault;
        }

        if (ex instanceof AuthenticationServiceException) {
            fault.setStatusCode(HttpServletResponse.SC_BAD_REQUEST);
            return fault;
        }

        fault.setStatusCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

        return fault;
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
        FaultDTO fault = getFault(failed);
        String body = mapper.writeValueAsString(fault);
        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(fault.getStatusCode());
        response.setHeader("Access-Control-Allow-Origin", "*");

        response.getWriter().write(body);
        response.getWriter().flush();
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException {

        Date expires = new Date(System.currentTimeMillis() + expirationTime);
        @SuppressWarnings("unchecked")
        List<RoleDTO> rolesDTO = (List<RoleDTO>) auth.getAuthorities();

        String[] roleIds = rolesDTO.stream().map(roleDTO -> roleDTO.getRoleId().toString()).toArray(String[]::new);

        UsuarioLoginLogDTO loginLogResponse = new UsuarioLoginLogDTO();

        try {
            UsuarioLoginLogCreateDTO request = UsuarioLoginLogCreateDTOBuilder.builder()
                    .withIdUsuario(((User) auth.getPrincipal()).getUsername())
                    .withRole(roleIds.length > 0 ? Integer.valueOf(roleIds[0]) : null)
                    .withDtLogin(LocalDateTime.now())
                    .build();

            loginLogResponse = usuarioLogService.createLog(request);
        } catch (Exception ex) {
            if (forceLogLogin) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Error Logging user login: " + ex.getMessage());
                throw ex;
            } else
                Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Error Logging user login: " + ex.getMessage());
        }

        String token = JWT.create()
                .withSubject(((User) auth.getPrincipal()).getUsername())
                .withArrayClaim("claims", roleIds)
                .withExpiresAt(expires)
                .sign(Algorithm.HMAC512(secret.getBytes()));

        res.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        AccessTokenResponse tokenResponse = new AccessTokenResponse();
        tokenResponse.setAccess_token(token);
        tokenResponse.setExpires_in(expirationTime);
        tokenResponse.setToken_type("bearer");
        tokenResponse.setClaims(rolesDTO);
        tokenResponse.setIdLoginLog(loginLogResponse.getId());

        String body = mapper.writeValueAsString(tokenResponse);

        res.setHeader("Access-Control-Allow-Origin", "*");

        res.getWriter().write(body);
        res.getWriter().flush();
    }
}
