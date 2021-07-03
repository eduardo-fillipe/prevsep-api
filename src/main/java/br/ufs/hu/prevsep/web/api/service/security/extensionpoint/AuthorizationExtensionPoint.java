package br.ufs.hu.prevsep.web.api.service.security.extensionpoint;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;

@FunctionalInterface
public interface AuthorizationExtensionPoint<T> {

    /**
     * This method checks if a authenticated user has access to a determined resource in the system
     *
     * @param authentication The authentication info
     * @param resource The resource that the user is trying to access
     * @throws AccessDeniedException If the user do not have access to the resource
     */
    void authorize(Authentication authentication, T resource) throws AccessDeniedException;
}
