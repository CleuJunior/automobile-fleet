package com.automobilefleet.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ErrorExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorEntity> entityNotFound(NotFoundException notFoundException) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        ErrorEntity err = ErrorEntity.builder()
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(notFoundException.getMessage())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorEntity> costumerBirthDateConstrainError() {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        ErrorEntity err =  ErrorEntity.builder()
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(ExceptionsConstants.DATE_CONSTRAIN_ERROR)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorEntity> costumerDriverLicenseDuplicate() {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        ErrorEntity err =  ErrorEntity.builder()
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(ExceptionsConstants.DRIVER_LICENSE_DUPLICATE)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(status).body(err);
    }

}
