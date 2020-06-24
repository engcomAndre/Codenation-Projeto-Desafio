package com.desafio.codenation.resources.exception;


import com.desafio.codenation.services.exception.AuthorizationException;
import com.desafio.codenation.services.exception.DataIntegrityException;
import com.desafio.codenation.services.exception.ObjectNotFoundException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.hibernate.validator.internal.engine.ConstraintViolationImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.naming.OperationNotSupportedException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.time.LocalDateTime;


@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
        StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), LocalDateTime.now(), e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);

    }

    @ExceptionHandler(DataIntegrityException.class)
    public ResponseEntity<StandardError> dataIntegrity(DataIntegrityException e, HttpServletRequest request) {
        StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), LocalDateTime.now(), e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
        ValidationError err = new ValidationError(HttpStatus.BAD_REQUEST.value(), LocalDateTime.now(), "Error de Validação");
        for (FieldError x : e.getBindingResult().getFieldErrors()) {
            err.addError(x.getField(), x.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<StandardError> validation(ConstraintViolationException e, HttpServletRequest request) {
        ValidationError err = new ValidationError(HttpStatus.BAD_REQUEST.value(), LocalDateTime.now(), "Erro de Validação");
        for (ConstraintViolation x : e.getConstraintViolations()) {
            err.addError(x.getConstraintDescriptor().toString(),x.getMessageTemplate());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<StandardError> validation(InvalidFormatException e, HttpServletRequest request) {
        StandardError err = new StandardError(HttpStatus.FORBIDDEN.value(), LocalDateTime.now(), e.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
    }

    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<StandardError> authorization(AuthorizationException e) {
        StandardError err = new StandardError(HttpStatus.FORBIDDEN.value(), LocalDateTime.now(), e.getLocalizedMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
    }
    @ExceptionHandler(OperationNotSupportedException.class)
    public ResponseEntity<StandardError> authorization(OperationNotSupportedException e) {
        StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), LocalDateTime.now(), e.getLocalizedMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

}
