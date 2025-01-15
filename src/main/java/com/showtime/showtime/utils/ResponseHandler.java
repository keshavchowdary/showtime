package com.showtime.showtime.utils;

import java.util.Collections;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ResponseHandler {

    public ResponseEntity<ApiResponse> successResponse(Object data, String message, int statusCode) {
        data = data == null ? Collections.emptyMap() : data;
        message = message == null ? "Success" : message;
        statusCode = statusCode == 0 ? HttpStatus.OK.value() : statusCode;
        ApiResponse apiResponse = new ApiResponse(
            data,
            message,
            statusCode
        );
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    public ResponseEntity<ApiResponse> errorResponse(Exception error, String message, int statusCode) {
        message = message == null ? "Success" : message;
        statusCode = statusCode == 0 ? HttpStatus.FORBIDDEN.value() : statusCode;
        ApiResponse apiResponse = new ApiResponse(
            error.getMessage(),
            message,
            statusCode
        );
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
    
}
