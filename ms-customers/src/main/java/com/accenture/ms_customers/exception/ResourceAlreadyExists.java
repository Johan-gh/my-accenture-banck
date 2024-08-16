package com.accenture.ms_customers.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ResourceAlreadyExists extends RuntimeException {
    public ResourceAlreadyExists(String resourceName) {
        super(String.format("El %s ya existe", resourceName));
    }
}
