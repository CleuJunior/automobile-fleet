package com.automobilefleet.exceptions;

import com.automobilefleet.exceptions.entity.ErrorEntity;
import com.automobilefleet.exceptions.notfoundexception.NotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.HashSet;

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
    public ResponseEntity<ErrorEntity> duplicateConstraintError(DataIntegrityViolationException exception) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String cause = exception.getMostSpecificCause().getMessage();

        ErrorEntity err =  ErrorEntity.builder()
                .status(status.value())
                .error(status.getReasonPhrase())
                .timestamp(LocalDateTime.now())
                .build();

        if (cause.contains("costumer_entity_driver_license_key")) {
            err.setMessage(ExceptionsConstants.DRIVER_LICENSE_DUPLICATE);
        }

        if (cause.contains("costumer_entity_email_key")) {
            err.setMessage(ExceptionsConstants.EMAIL_DUPLICATE);
        }

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorEntity> phoneRegexError(MethodArgumentNotValidException exception) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        System.out.println("***************************************************");
        System.out.println("***************************************************");
        System.out.println("***************************************************");
        System.out.println("***************************************************");
        System.out.println(exception.getParameter());
        System.out.println("***************************************************");
        System.out.println("***************************************************");
        System.out.println("***************************************************");
        System.out.println("***************************************************");
        System.out.println(exception.getMessage());
        System.out.println("***************************************************");
        System.out.println("***************************************************");
        System.out.println("***************************************************");
        System.out.println("***********************getCode()*******************");
        System.out.println(exception.getAllErrors().get(0).toString().equals("Cleonildo"));
        System.out.println("***************************************************");
        System.out.println("***************************************************");
        System.out.println("***************************************************");
        System.out.println("***************************************************");
        System.out.println(exception.getBindingResult());
        System.out.println("***************************************************");
        System.out.println("***************************************************");
        System.out.println("***************************************************");
        System.out.println("***************************************************");
        System.out.println("**********************FIM**************************");

        ErrorEntity err =  ErrorEntity.builder()
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(ExceptionsConstants.PHONE_REGEX_ERROR)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(status).body(err);
    }
}
