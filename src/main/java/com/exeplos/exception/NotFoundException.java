package com.exeplos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
    private static final long serialVersionUID = 5157162697957266799L;

    public NotFoundException() {
    }

    public NotFoundException(String message) {
        super(message);
    }
}
