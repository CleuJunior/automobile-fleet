package br.com.comment.work.config;

import lombok.Getter;

@Getter
public class HttpErrorStatusException extends RuntimeException {

    private final int status;

    public HttpErrorStatusException(String message, int status) {
        super(message);
        this.status = status;
    }

}
