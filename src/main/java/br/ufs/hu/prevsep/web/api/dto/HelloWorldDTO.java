package br.ufs.hu.prevsep.web.api.dto;

import br.ufs.hu.prevsep.web.api.exception.UserNotFoundException;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public class HelloWorldDTO {
    private String message;
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime time;

    public void setMessage(String message) {
        this.message = message;
//        throw new UnsupportedOperationException("Not Implemented Yet.");
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }
}
