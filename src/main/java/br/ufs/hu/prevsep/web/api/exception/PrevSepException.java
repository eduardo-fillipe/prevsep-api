package br.ufs.hu.prevsep.web.api.exception;

import org.springframework.http.HttpStatus;

public abstract class PrevSepException extends RuntimeException{
    private final HttpStatus httpStatus;
    private String message;
    private String detailedMessage;

    public PrevSepException(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public PrevSepException setMessage(String message) {
        this.message = message;
        return this;
    }

    public PrevSepException setDetailedMessage(String detailedMessage) {
        this.detailedMessage = detailedMessage;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public String getDetailedMessage() {
        return detailedMessage;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
