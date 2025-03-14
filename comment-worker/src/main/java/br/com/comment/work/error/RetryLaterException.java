package br.com.comment.work.error;

public class RetryLaterException extends RuntimeException {
    public RetryLaterException(String message) {
        super(message);
    }
}

