package br.ufs.hu.prevsep.web.api.exception.user;

import br.ufs.hu.prevsep.web.api.exception.PrevSepException;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends PrevSepException {

    public UserNotFoundException() {
        super(HttpStatus.NOT_FOUND);
        this.withMessage("Not found");
    }
}
