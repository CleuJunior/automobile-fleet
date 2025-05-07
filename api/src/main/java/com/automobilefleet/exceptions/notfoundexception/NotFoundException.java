package com.automobilefleet.exceptions.notfoundexception;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {
    private final Object[] args;

    public NotFoundException(String message, Object... args) {
        super(message);
        this.args = args;
    }
}
