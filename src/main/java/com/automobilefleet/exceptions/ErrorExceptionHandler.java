package com.automobilefleet.exceptions;

import com.automobilefleet.exceptions.entity.ErrorResponse;
import com.automobilefleet.exceptions.entity.MultiplesErrorsResponse;
import com.automobilefleet.exceptions.notfoundexception.NotFoundException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@ControllerAdvice
public class ErrorExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> entityNotFound(NotFoundException notFoundException) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        ErrorResponse err = ErrorResponse.builder()
                .status(status.value())
                .statusErrorMessage(status.getReasonPhrase())
                .message(notFoundException.getMessage())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> costumerBirthDateConstrainError() {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        ErrorResponse err =  ErrorResponse.builder()
                .status(status.value())
                .statusErrorMessage(status.getReasonPhrase())
                .message(ExceptionsConstants.DATE_CONSTRAIN_ERROR)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> duplicateConstraintError(DataIntegrityViolationException exception) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String cause = exception.getMostSpecificCause().getMessage();

        ErrorResponse err = ErrorResponse.builder()
                .status(status.value())
                .statusErrorMessage(status.getReasonPhrase())
                .timestamp(LocalDateTime.now())
                .build();

        String costumerEntityDriverLicenseKeyConstraint = "costumer_entity_driver_license_key";
        String costumerEntityEmailKeyContraint = "costumer_entity_email_key";

        if (cause.contains(costumerEntityDriverLicenseKeyConstraint))
            err.setMessage(ExceptionsConstants.DRIVER_LICENSE_DUPLICATE);

        if (cause.contains(costumerEntityEmailKeyContraint))
            err.setMessage(ExceptionsConstants.EMAIL_DUPLICATE);

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MultiplesErrorsResponse> constraintErrosMessages(MethodArgumentNotValidException exception) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        String errorMessages = exception.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(" | "));

        MultiplesErrorsResponse err =  MultiplesErrorsResponse.builder()
                .status(status.value())
                .statusErrorMessage(status.getReasonPhrase())
                .messages(errorMessages)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(status).body(err);
    }
}