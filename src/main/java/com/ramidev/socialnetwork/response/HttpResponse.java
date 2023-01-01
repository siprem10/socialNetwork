package com.ramidev.socialnetwork.response;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class HttpResponse {

    private HttpStatus status;
    private String message;
    private Object data;

    public HttpResponse(String message, Object object) {
        this.status = HttpStatus.OK;
        this.message = message;
        this.data = object;
    }

    public HttpResponse created() {
        this.status = HttpStatus.CREATED;
        return this;
    }

    public HttpResponse(String message) {
        this.status = HttpStatus.OK;
        this.message = message;
    }
}
