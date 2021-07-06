package br.ufs.hu.prevsep.web.api.config.security.filter;

import br.ufs.hu.prevsep.web.api.config.ApiRequestMappings;
import br.ufs.hu.prevsep.web.api.model.UsuarioAcessoLogEntity;
import br.ufs.hu.prevsep.web.api.repository.UsuarioEventLogRepository;
import org.springframework.core.annotation.Order;
import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@Order(1)
public class EventLogFilter extends OncePerRequestFilter {
    private final UsuarioEventLogRepository usuarioEventLogRepository;

    public EventLogFilter(UsuarioEventLogRepository usuarioEventLogRepository) {
        this.usuarioEventLogRepository = usuarioEventLogRepository;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        return !path.startsWith(ApiRequestMappings.API_BASE_ENDPOINT);
    }

    @Override
    protected void doFilterInternal(@NonNull  HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        UsuarioAcessoLogEntity eventLogEntity = new UsuarioAcessoLogEntity();

        try { doSecurityIn(request, eventLogEntity); } catch (Exception ignored) {}

        filterChain.doFilter(request, response);

        try {doSecurityOut(response, eventLogEntity);} catch (Exception ignored) {}

    }

    private void doSecurityIn(HttpServletRequest request, UsuarioAcessoLogEntity eventLogEntity) throws IOException{
        String idLog = UUID.randomUUID().toString();
        eventLogEntity.setIdLog(idLog);
        eventLogEntity.setDtRequisicao(LocalDateTime.now());
        eventLogEntity.setVerboRequisicao(request.getMethod());
        eventLogEntity.setOperation(request.getRequestURI());
        eventLogEntity.setUriRequisicao(request.getRequestURL().toString());
        eventLogEntity.setIdUsuario(String.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
        String requestBody = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        eventLogEntity.setBodyRequisicao(!requestBody.isBlank() ? requestBody : null);
        // usuarioEventLogRepository.save(eventLogEntity);
    }

    private void doSecurityOut(HttpServletResponse response, UsuarioAcessoLogEntity eventLogEntity) {
        eventLogEntity.setStatusResponse(response.getStatus());
        usuarioEventLogRepository.save(eventLogEntity);
    }
}
