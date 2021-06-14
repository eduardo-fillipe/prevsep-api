package br.ufs.hu.prevsep.web.api.exception;

import org.springframework.http.HttpStatus;

public class InvalidFormStateException extends PrevSepException {
    public InvalidFormStateException() {
        super(HttpStatus.CONFLICT);
    }
}
