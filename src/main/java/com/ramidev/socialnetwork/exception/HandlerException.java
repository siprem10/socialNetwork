package com.ramidev.socialnetwork.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class HandlerException extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fielName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fielName, message);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> notFoundException(NotFoundException ex) {
        return builder(new BuilderException(ex.getMessage(), HttpStatus.NOT_FOUND));
    }

    @ExceptionHandler(UniqueException.class)
    public ResponseEntity<Object> uniqueException(UniqueException ex) {
        return builder(new BuilderException(ex.getMessage(), HttpStatus.OK));
    }


    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<Object> forbiddenException(ForbiddenException ex) {
        return builder(new BuilderException(ex.getMessage(), HttpStatus.FORBIDDEN));
    }

    private ResponseEntity<Object> builder(BuilderException builderException) {
        return new ResponseEntity<>(builderException, builderException.getStatus());
    }
}
