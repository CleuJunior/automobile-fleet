package com.automobilefleet.exceptions;

import com.automobilefleet.exceptions.entity.ErrorResponse;
import com.automobilefleet.exceptions.notfoundexception.NotFoundException;
import com.automobilefleet.exceptions.policyexception.PolicyException;
import com.automobilefleet.i18n.LocalizedMessageTranslationService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import static com.automobilefleet.exceptions.ConstraintViolationEnum.constraintFromKey;
import static com.automobilefleet.exceptions.ExceptionsConstants.DATE_CONSTRAIN_ERROR;
import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.METHOD_NOT_ALLOWED;
import static org.springframework.http.HttpStatus.NOT_FOUND;


@ControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class ErrorExceptionHandler {
    private final LocalizedMessageTranslationService localizedMessageTranslationService;

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> entityNotFoundHandler(HttpServletRequest request, NotFoundException cause) {
        var message = localizedMessageTranslationService.translateMessage(cause);
        var err = new ErrorResponse(NOT_FOUND.value(), NOT_FOUND.getReasonPhrase(), message, request.getRequestURI(), now());

        log.error("{} URI: {}", NOT_FOUND, request.getRequestURI());
        return ResponseEntity.status(NOT_FOUND).body(err);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> costumerBirthDateConstrainHandler(HttpServletRequest request) {
        var err = new ErrorResponse(BAD_REQUEST.value(), BAD_REQUEST.getReasonPhrase(), DATE_CONSTRAIN_ERROR, request.getRequestURI(), now());

        return ResponseEntity.status(BAD_REQUEST).body(err);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> duplicateConstraintErrorHandler(HttpServletRequest request, ConstraintViolationException cause) {
        var err = new ErrorResponse(BAD_REQUEST.value(), BAD_REQUEST.getReasonPhrase(), constraintFromKey(cause.getConstraintName()), request.getRequestURI(), now());

        log.error("Constraint error: {}", constraintFromKey(cause.getConstraintName()));
        return ResponseEntity.status(BAD_REQUEST).body(err);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> httpRequestMethodNotSupportedHandler(HttpServletRequest request, HttpRequestMethodNotSupportedException exception) {
        var err = new ErrorResponse(METHOD_NOT_ALLOWED.value(), METHOD_NOT_ALLOWED.getReasonPhrase(), exception.getBody().getDetail(), request.getRequestURI(), now());

        log.error("The method {} is not supported in this endpoint: {}", exception.getMethod(), request.getRequestURI());
        return ResponseEntity.status(METHOD_NOT_ALLOWED).body(err);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorResponse> noResourceFoundHandler(NoResourceFoundException exception) {
        var err = new ErrorResponse(NOT_FOUND.value(), NOT_FOUND.getReasonPhrase(), exception.getBody().getDetail(), exception.getResourcePath(), now());

        log.error("This resource path: {} has no static resource", exception.getResourcePath());
        return ResponseEntity.status(NOT_FOUND).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> constraintErrorMessagesHandler(HttpServletRequest request, MethodArgumentNotValidException exception) {
        var errorMessage = getErrorMessage(exception);
        var err = new ErrorResponse(BAD_REQUEST.value(), BAD_REQUEST.getReasonPhrase(), errorMessage, request.getRequestURI(), now());

        log.error("Constraint error: {} On: {}", errorMessage, request.getRequestURI());
        return ResponseEntity.status(BAD_REQUEST).body(err);
    }

    private String getErrorMessage(MethodArgumentNotValidException exception) {
        return exception.getBindingResult().getAllErrors().stream()
                .findFirst()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .orElse("Unknown Error");
    }

    @ExceptionHandler(PolicyException.class)
    public ResponseEntity<ErrorResponse> policyErrorHandler(HttpServletRequest request, PolicyException exception) {
        var message = localizedMessageTranslationService.translateMessage(exception.getMessage());
        var err = new ErrorResponse(BAD_REQUEST.value(), BAD_REQUEST.getReasonPhrase(), message, request.getRequestURI(), now());

        log.error("Policy error: {} On: {}", exception.getMessage(), request.getRequestURI());
        return ResponseEntity.status(BAD_REQUEST).body(err);
    }

    @ExceptionHandler(PasswordMatchException.class)
    public ResponseEntity<ErrorResponse> passwordMatchHandler(HttpServletRequest request, PasswordMatchException passwordMatchException) {
        var message = localizedMessageTranslationService.translateMessage(passwordMatchException.getMessage());
        var err = new ErrorResponse(BAD_REQUEST.value(), BAD_REQUEST.getReasonPhrase(), message, request.getRequestURI(), now());

        log.error("{} URI: {}", BAD_REQUEST, request.getRequestURI());
        return ResponseEntity.status(BAD_REQUEST).body(err);
    }
}