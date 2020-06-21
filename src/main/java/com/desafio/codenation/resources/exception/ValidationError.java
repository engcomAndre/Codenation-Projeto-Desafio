package com.desafio.codenation.resources.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class ValidationError extends StandardError {

    private static final long serialVersionUID = 1L;


    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(Integer status, LocalDateTime timeStamp, String msg) {
        super(status, timeStamp, msg);
    }

    public List<FieldMessage> getErrors() {
        return errors;
    }

    public void addError(String fieldName, String message) {
        this.errors.add(new FieldMessage(fieldName, message));
    }
}