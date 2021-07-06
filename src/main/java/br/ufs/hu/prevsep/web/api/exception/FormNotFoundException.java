package br.ufs.hu.prevsep.web.api.exception;

import org.springframework.http.HttpStatus;

public class FormNotFoundException extends PrevSepException {
    public FormNotFoundException() {
        super(HttpStatus.NOT_FOUND);
        withMessage("Formulário não encontrado");
    }
}
