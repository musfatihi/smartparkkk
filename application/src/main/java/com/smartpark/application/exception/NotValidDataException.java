package com.smartpark.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotValidDataException extends RuntimeException {

    public NotValidDataException() {
        super();
    }

    public NotValidDataException(final String message) {
        super(message);
    }

}

