package com.automobilefleet.exceptions.notfoundexception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
