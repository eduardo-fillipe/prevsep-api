package br.ufs.hu.prevsep.web.api.service.security.extensionpoint;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;

@FunctionalInterface
public interface AuthorizationExtensionPoint<T> {

    /**
     * Esse método checa se um usuário autenticado tem acesso para determinado recurso no sistema
     *
     * @param authentication A informação de autenticação
     * @param resource O recurso que o usuário está tentando acessar
     * @throws AccessDeniedException Se o usuário não tem acesso ao recurso
     */
    void authorize(Authentication authentication, T resource) throws AccessDeniedException;
}
