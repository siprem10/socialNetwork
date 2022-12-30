package com.ramidev.socialnetwork.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter @Setter
public class BuilderException {

    private HttpStatus status;
    private String error;

    public BuilderException(String error, HttpStatus status) {
        this.status = status;
        this.error = error;
    }

    public BuilderException(String error) {
        this.error = error;
    }
}
