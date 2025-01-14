package com.showtime.showtime.utils;

import lombok.Data;

@Data
public class ApiResponse {
    private Object data;
    private String message;
    private int statusCode;
    public ApiResponse(Object data, String message, int statusCode) {
        this.data = data;
        this.message = message;
        this.statusCode = statusCode;
    }
}
