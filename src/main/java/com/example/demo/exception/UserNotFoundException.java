package com.example.demo.exception;

import lombok.Data;

import java.util.UUID;

@Data
public class UserNotFoundException extends RuntimeException {

    private final String errorMessage;
    private final UUID id;

    @Override
    public String getMessage() {
        return errorMessage + " - " + id;
    }
}
