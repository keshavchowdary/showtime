package com.showtime.showtime.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse> handleRequestBodyEmpty(HttpMessageNotReadableException exception) {
        Object data = Collections.emptyMap();
        return new ResponseEntity<>(new ApiResponse(data, "Invalid request body", 404), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handlePayloadValidation(MethodArgumentNotValidException exception) {
        List<String> errors = new ArrayList<>();
        exception.getBindingResult().getFieldErrors().forEach(error -> {
            errors.add(error.getDefaultMessage());
        });
        String message = String.join(". ", errors);
        Object data = Collections.emptyMap();
        return new ResponseEntity<>(new ApiResponse(data, message.toString(), 404), HttpStatus.NOT_FOUND);
    }
}
