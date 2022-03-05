package com.github.ffremont.mfa.resource;

import com.github.ffremont.mfa.ResourceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceAdvice {
    @ExceptionHandler(ResourceException.class)
    public ResponseEntity handleConflict(ResourceException e) {
        return ResponseEntity.status(e.getStatus()).build();
    }
}
