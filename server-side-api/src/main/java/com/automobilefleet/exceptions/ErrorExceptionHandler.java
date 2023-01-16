package com.automobilefleet.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ErrorExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorEntity> entityNotFound(NotFoundException exception) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        ErrorEntity err = ErrorEntity.builder()
                                        .timestamp(Instant.now())
                                        .status(status.value())
                                        .message(exception.getMessage())
                                        .build();

        return ResponseEntity.status(status).body(err);
    }

}
