package br.ufs.hu.prevsep.web.api.exception;

import org.springframework.http.HttpStatus;

public class FormValidationException extends PrevSepException {
    public FormValidationException() {
        super(HttpStatus.BAD_REQUEST);
        this.withMessage("This form is invalid.");
    }
}
