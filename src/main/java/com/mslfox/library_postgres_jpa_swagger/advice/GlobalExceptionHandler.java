package com.mslfox.library_postgres_jpa_swagger.advice;

import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.util.Arrays;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return ResponseEntity.badRequest()
                .body("400 BAD_REQUEST \"Validation failure\"" + Arrays.toString(ex.getDetailMessageArguments()));
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<?> handleValidationException(HandlerMethodValidationException ex) {
        return ResponseEntity.badRequest()
                .body(ex.getMessage() + Arrays.toString(ex.getDetailMessageArguments()));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handleBadRequestException(BadRequestException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}