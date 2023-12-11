package com.duvi.myessen.domain;

import java.util.List;

import org.springframework.validation.ObjectError;

public class ApiError {
    private List<ObjectError> errors;

    public ApiError(List<ObjectError> errors) {
        this.errors = errors;
    }

    public List<ObjectError> getErrors() {
        return this.errors;
    }
    
    public void setErrors(List<ObjectError> errors) {
        this.errors = errors;
    }
 }
