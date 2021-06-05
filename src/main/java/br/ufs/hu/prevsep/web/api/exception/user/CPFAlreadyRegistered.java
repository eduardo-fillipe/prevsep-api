package br.ufs.hu.prevsep.web.api.exception.user;

import br.ufs.hu.prevsep.web.api.exception.PrevSepException;
import org.springframework.http.HttpStatus;

public class CPFAlreadyRegistered extends PrevSepException {

    public CPFAlreadyRegistered() {
        super(HttpStatus.CONFLICT);
        this.withMessage("This CPF is already in use.");
    }
}
