package br.ufs.hu.prevsep.web.api.exception;

import br.ufs.hu.prevsep.web.api.dto.fault.FieldErrorDTO;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

public abstract class PrevSepException extends RuntimeException{
    private final HttpStatus httpStatus;
    private String message;
    private String detailedMessage;
    private List<FieldErrorDTO> fieldErrors;

    public PrevSepException(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public PrevSepException withMessage(String message) {
        this.message = message;
        return this;
    }

    public PrevSepException withDetailedMessage(String detailedMessage) {
        this.detailedMessage = detailedMessage;
        return this;
    }

    public PrevSepException withFieldError(String fieldName, String error) {
        if (this.fieldErrors == null)
            this.fieldErrors = new ArrayList<>();
        this.fieldErrors.add(new FieldErrorDTO(fieldName, error));
        return this;
    }

    public String getMessage() {
        return message;
    }

    public String getDetailedMessage() {
        return detailedMessage;
    }

    public List<FieldErrorDTO> getFieldErrors() {
        return fieldErrors;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
