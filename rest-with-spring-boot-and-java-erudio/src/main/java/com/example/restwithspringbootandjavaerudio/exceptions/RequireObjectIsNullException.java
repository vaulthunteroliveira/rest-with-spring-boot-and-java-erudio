package com.example.restwithspringbootandjavaerudio.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequireObjectIsNullException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1918039518163403923L;

    public RequireObjectIsNullException(String ex) {
        super(ex);
    }

    public RequireObjectIsNullException() {
        super("It's not to persist a null object!");
    }
}
