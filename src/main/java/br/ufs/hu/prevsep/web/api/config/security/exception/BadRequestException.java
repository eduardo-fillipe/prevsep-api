package br.ufs.hu.prevsep.web.api.config.security.exception;

import org.springframework.security.core.AuthenticationException;

public class BadRequestException extends AuthenticationException {

    public BadRequestException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public BadRequestException(String msg) {
        super(msg);
    }
}
