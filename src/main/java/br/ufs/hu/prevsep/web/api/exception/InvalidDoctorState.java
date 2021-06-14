package br.ufs.hu.prevsep.web.api.exception;

import org.springframework.http.HttpStatus;

public class InvalidDoctorState extends PrevSepException{
    public InvalidDoctorState() {
        super(HttpStatus.CONFLICT);
        withMessage("The doctor state on the system is invalid for this request.");
    }
}
