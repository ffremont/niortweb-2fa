package com.github.ffremont.mfa;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class ResourceException extends RuntimeException{
    private final int status;

    public ResourceException(int status) {
        this.status = status;
    }

    public ResourceException(String message, int status) {
        super(message);
        this.status = status;
    }

    public ResourceException(String message, Throwable cause, int status) {
        super(message, cause);
        this.status = status;
    }
}
