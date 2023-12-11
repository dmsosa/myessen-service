package com.duvi.myessen.controller.exception;


import java.util.Collections;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.WebUtils;

import com.duvi.myessen.controller.exception.food.FoodExistsException;
import com.duvi.myessen.controller.exception.food.FoodNotFoundException;
import com.duvi.myessen.domain.ApiError;

import io.micrometer.common.lang.Nullable;


//GlobalException > ExceptionHandler (delegating exceptions) > SpecificExpHandler (create body of the response) > InternalHandler (send the response)
@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler
    public final ResponseEntity<ApiError> exceptionHandler(Exception ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        

        //DB Exceptions
        if (ex instanceof DataIntegrityViolationException) {
            HttpStatus status = HttpStatus.BAD_REQUEST;
            DataIntegrityViolationException dive = (DataIntegrityViolationException) ex;
            return diveHandler(dive, headers, status, request);
        }
        //Food exceptions
        else if (ex instanceof FoodNotFoundException) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            FoodNotFoundException fnfe = (FoodNotFoundException) ex;
            return fnfeHandler(fnfe, headers, status, request);
        }
        //Other exceptions
        else {
            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
            return internalExceptionHandler(ex, null, headers, status, request);
        }
    }
    //DB exceptions handler
    public final ResponseEntity<ApiError> diveHandler(DataIntegrityViolationException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<ObjectError> errors = Collections.singletonList(new ObjectError("Data Integrity Violation Exception", ex.getMessage()));
        ApiError error = new ApiError(errors);
        return internalExceptionHandler(ex, error, headers, status, request);
    }
    //Food exceptions handler
    public final ResponseEntity<ApiError> fnfeHandler(FoodNotFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ObjectError errors = new ObjectError("Food Not Found Exception", ex.getMessage());
        ApiError body = new ApiError(Collections.singletonList(errors));
        return internalExceptionHandler(ex, body, headers, status, request);
    }
    public final ResponseEntity<ApiError> feeHandler(FoodExistsException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ObjectError errors = new ObjectError("Food Already Exists Exception", ex.getMessage());
        ApiError body = new ApiError(Collections.singletonList(errors));
        return internalExceptionHandler(ex, body, headers, status, request);
    }

    //Internal Exceptions Handler (returning the response)

    public final ResponseEntity<ApiError> internalExceptionHandler(Exception ex, @Nullable ApiError body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (status.equals(HttpStatus.INTERNAL_SERVER_ERROR)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }
        return new ResponseEntity<>(body, headers, status);
    }
}
