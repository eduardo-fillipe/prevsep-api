package br.ufs.hu.prevsep.web.api.config.security.filter;

import br.ufs.hu.prevsep.web.api.config.ApiRequestMappings;
import br.ufs.hu.prevsep.web.api.config.security.exception.UnauthorizedException;
import br.ufs.hu.prevsep.web.api.dto.security.authorization.role.RoleDTO;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class OAuth2JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private final String TOKEN_PREFIX = "Bearer ";
    private final String secret;

    public OAuth2JWTAuthorizationFilter(AuthenticationManager authManager, String secret) {
        super(authManager);
        this.secret = secret;
    }

    public OAuth2JWTAuthorizationFilter(AuthenticationManager authenticationManager, AuthenticationEntryPoint authenticationEntryPoint, String secret) {
        super(authenticationManager, authenticationEntryPoint);
        this.secret = secret;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        return !path.startsWith(ApiRequestMappings.API_BASE_ENDPOINT);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(HttpHeaders.AUTHORIZATION);
        try {
            if (header == null || !header.startsWith(TOKEN_PREFIX)) {
                throw new UnauthorizedException("AUTHORIZATION Header is mandatory.");
            }

            UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            res.setHeader("Access-Control-Allow-Origin", "*");
            chain.doFilter(req, res);
        } catch (Exception e) {
            getAuthenticationEntryPoint().commence(req, res, new InternalAuthenticationServiceException("Failure during authorization.", e));
        }
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (token != null) {
            DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(secret.getBytes()))
                    .build()
                    .verify(token.replace(TOKEN_PREFIX, ""));

            List<RoleDTO> claims = decodedJWT
                    .getClaim("claims").asList(String.class).stream().map(s -> {
                        RoleDTO r = new RoleDTO();
                        r.setRoleId(Integer.valueOf(s));
                        return r;
                    }).collect(Collectors.toList());

            String user = decodedJWT.getSubject();

            if (user != null)
                return new UsernamePasswordAuthenticationToken(user, null, claims);

            return null;
        }
        return null;
    }
}