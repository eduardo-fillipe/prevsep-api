package br.ufs.hu.prevsep.web.api.service.security;

import br.ufs.hu.prevsep.web.api.service.security.extensionpoint.AuthorizationExtensionPoint;
import org.springframework.context.ApplicationContext;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationExtensionService {
    private final ApplicationContext ctx;

    public AuthorizationExtensionService(ApplicationContext ctx) {
        this.ctx = ctx;
    }

    public <T, K extends AuthorizationExtensionPoint<T>> void authorize(Class<K> extension, T resource) throws AccessDeniedException {
        if (extension == null)
            throw new IllegalArgumentException("The Extension point can not be null.");

        AuthorizationExtensionPoint<T> authorizationExtensionPoint = ctx.getBean(extension);
        authorizationExtensionPoint.authorize(SecurityContextHolder.getContext().getAuthentication(), resource);
    }

    public <T> void authorize(T resource, AuthorizationExtensionPoint<T> extensionPoint) throws AccessDeniedException {
        if (extensionPoint == null)
            throw new IllegalArgumentException("The Extension point can not be null.");

        extensionPoint.authorize(SecurityContextHolder.getContext().getAuthentication(), resource);
    }
}
