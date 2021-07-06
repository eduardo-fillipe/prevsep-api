package br.ufs.hu.prevsep.web.api.exception;

import org.springframework.http.HttpStatus;

public class InvalidDoctorState extends PrevSepException{
    public InvalidDoctorState() {
        super(HttpStatus.CONFLICT);
        withMessage("O estado do médico no sistema é inválido para essa solicitação.");
    }
}
