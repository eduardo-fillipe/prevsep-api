package br.ufs.hu.prevsep.web.api.config.security.filter;

import br.ufs.hu.prevsep.web.api.config.security.exception.UnauthorizedException;
import br.ufs.hu.prevsep.web.api.dto.fault.ErrorDetailDTO;
import br.ufs.hu.prevsep.web.api.dto.fault.FaultDTO;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@Component
public class BasicAuthorizationEntrypoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        FaultDTO response = getFault(e);

        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        httpServletResponse.setStatus(response.getStatusCode());

        OutputStream out = httpServletResponse.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        System.out.println(httpServletResponse.getHeaderNames());
        mapper.writeValue(out, response);
        out.flush();
    }

    private FaultDTO getFault(AuthenticationException e) {
        FaultDTO response = new FaultDTO();

        if (e.getCause() == null) {
            response.setStatusCode(HttpStatus.UNAUTHORIZED.value());
            response.setMessage(HttpStatus.UNAUTHORIZED.getReasonPhrase());
            response.setError(e.getClass().getSimpleName());
            ErrorDetailDTO errorDetailDTO = new ErrorDetailDTO();
            errorDetailDTO.setErrorDetail(e.getMessage());
            errorDetailDTO.setExceptionClass(e.getClass().getName());
            response.setErrorDetail(errorDetailDTO);
            return response;
        }

        if (e.getCause() instanceof JWTVerificationException) {
            response.setStatusCode(HttpStatus.FORBIDDEN.value());
            response.setMessage(HttpStatus.FORBIDDEN.getReasonPhrase());
            response.setError(e.getCause().getClass().getSimpleName());
            ErrorDetailDTO errorDetailDTO = new ErrorDetailDTO();
            errorDetailDTO.setErrorDetail(e.getCause().getMessage());
            errorDetailDTO.setExceptionClass(e.getCause().getClass().getName());
            response.setErrorDetail(errorDetailDTO);
            return response;
        }

        if (e.getCause() instanceof UnauthorizedException) {
            response.setStatusCode(HttpStatus.UNAUTHORIZED.value());
            response.setMessage(HttpStatus.UNAUTHORIZED.getReasonPhrase());
            response.setError(e.getCause().getClass().getSimpleName());
            ErrorDetailDTO errorDetailDTO = new ErrorDetailDTO();
            errorDetailDTO.setErrorDetail(e.getCause().getMessage());
            errorDetailDTO.setExceptionClass(e.getCause().getClass().getName());
            response.setErrorDetail(errorDetailDTO);
            return response;
        }

        response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        response.setError(e.getClass().getSimpleName());
        return response;
    }
}
