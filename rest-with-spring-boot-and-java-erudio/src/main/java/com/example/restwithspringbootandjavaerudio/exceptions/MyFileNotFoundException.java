package com.example.restwithspringbootandjavaerudio.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MyFileNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1268317189745546993L;

    public MyFileNotFoundException(String ex) {
        super(ex);
    }

    public MyFileNotFoundException(String ex, Throwable cause) {
        super(ex, cause);
    }
}
