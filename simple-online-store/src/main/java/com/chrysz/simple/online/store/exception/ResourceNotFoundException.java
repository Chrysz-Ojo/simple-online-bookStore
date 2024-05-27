package com.chrysz.simple.online.store.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ResourceNotFoundException extends RuntimeException {
    private final HttpStatus status;

    public ResourceNotFoundException(String message, HttpStatus notFound) {
        super(message);
        this.status = HttpStatus.NOT_FOUND;
    }

    public HttpStatus getStatus() {
        return status;
    }


}
