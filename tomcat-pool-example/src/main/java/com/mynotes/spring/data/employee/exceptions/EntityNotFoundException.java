package com.mynotes.spring.data.employee.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Could not find entity with id.")
public class EntityNotFoundException extends Exception {


    public EntityNotFoundException(String message) {
        super(message);
    }

}
