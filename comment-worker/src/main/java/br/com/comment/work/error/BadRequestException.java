package br.com.comment.work.error;

import static java.lang.String.format;

public class BadRequestException extends RuntimeException {

    public BadRequestException(String message, Object... args) {
        super(format(message, args));
    }
}