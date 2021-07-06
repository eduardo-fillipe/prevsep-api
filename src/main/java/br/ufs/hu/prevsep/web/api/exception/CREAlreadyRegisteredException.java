package br.ufs.hu.prevsep.web.api.exception;

import org.springframework.http.HttpStatus;

public class CREAlreadyRegisteredException extends PrevSepException {
    public CREAlreadyRegisteredException() {
        super(HttpStatus.CONFLICT);
        this.withMessage("Esse CRE já está registrado.");
    }
}
