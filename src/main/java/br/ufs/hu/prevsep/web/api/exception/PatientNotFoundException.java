package br.ufs.hu.prevsep.web.api.exception;

import org.springframework.http.HttpStatus;

public class PatientNotFoundException extends PrevSepException {
    public PatientNotFoundException() {
        super(HttpStatus.NOT_FOUND);
        withMessage("Patient not found");
    }
}
