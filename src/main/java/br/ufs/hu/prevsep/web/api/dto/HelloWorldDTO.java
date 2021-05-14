package br.ufs.hu.prevsep.web.api.dto;

import br.ufs.hu.prevsep.web.api.exception.UserNotFoundException;

import java.time.LocalDateTime;

public class HelloWorldDTO {
    private String message;
    private LocalDateTime time;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
        throw new UserNotFoundException()
                .setMessage("User not found")
                .setDetailedMessage("User not found in database.");
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
