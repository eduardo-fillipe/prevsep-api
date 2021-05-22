package br.ufs.hu.prevsep.web.api.config.security.exception;

import org.springframework.security.core.AuthenticationException;

public class ContentTypeNotAllowedException extends AuthenticationException {
    public ContentTypeNotAllowedException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public ContentTypeNotAllowedException(String msg) {
        super(msg);
    }
}
