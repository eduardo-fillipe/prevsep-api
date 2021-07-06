package br.ufs.hu.prevsep.web.api.exception;

import org.springframework.http.HttpStatus;

public class PasswordDoesNotHaveMinimumRequirementsException extends PrevSepException {
    public PasswordDoesNotHaveMinimumRequirementsException() {
        super(HttpStatus.BAD_REQUEST);
        this.withMessage("Senha não satisfaz os requisitos mínimos.");
    }
}
