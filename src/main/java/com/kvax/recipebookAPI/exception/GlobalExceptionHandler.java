package com.kvax.recipebookAPI.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handlerException(ConstraintViolationException e, ServletWebRequest request) {
        final List<Object> errors = new ArrayList<>();
        e.getConstraintViolations().stream().forEach(fieldError -> {
            Map<String, Object> error = new HashMap<>();
            error.put("path", String.valueOf(fieldError.getPropertyPath()));
            error.put("message", fieldError.getMessage());
            errors.add(error);
        });
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("path", request.getRequest().getRequestURI());
        body.put("message", "Invalid JSON");
        body.put("error", "Validation failed");
        body.put("errors", errors);
        body.put("status", 400);

        return new ResponseEntity<>(body, httpStatus);
    }
    
    /*
     * 	@ExceptionHandler(ConstraintViolationException.class)
	public final ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException e) {
	    return new ResponseEntity<>("not valid due to validation error: " + e.getMessage(), HttpStatus.BAD_REQUEST);

	}
     */
}
