package br.ufs.hu.prevsep.web.api.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends PrevSepException {

    public UserNotFoundException() {
        super(HttpStatus.NOT_FOUND);
    }
}
