package br.ufs.hu.prevsep.web.api.exception;

import org.springframework.http.HttpStatus;

public class CRMAlreadyRegisteredException extends PrevSepException {
    public CRMAlreadyRegisteredException() {
        super(HttpStatus.CONFLICT);
        this.withMessage("Esse CRM já está registrado.");
    }
}
