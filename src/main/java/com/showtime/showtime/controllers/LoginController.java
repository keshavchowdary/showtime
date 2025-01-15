package com.showtime.showtime.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.showtime.showtime.constants.LoginConstants;
import com.showtime.showtime.entities.requestEntities.UserLogin;
import com.showtime.showtime.entities.requestEntities.UserRegistration;
import com.showtime.showtime.services.JWTService;
import com.showtime.showtime.services.LoginService;
import com.showtime.showtime.utils.ApiResponse;
import com.showtime.showtime.utils.ResponseHandler;

import jakarta.validation.Valid;

@RestController
public class LoginController {

    @Autowired
    private ResponseHandler responseHandler;
    @Autowired
    private JWTService jwtService;
    @Autowired
    private LoginService loginService;

    @GetMapping("/health")
    public ResponseEntity<ApiResponse> healthCheck() {
        return responseHandler.successResponse(
            null,
            "Health check successful",
            200
        );
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> userLogin(@RequestBody UserLogin userLogin) {
        try {
            Map<String, Object> claims = new HashMap<>();
            claims.put("email", userLogin.getEmail());
            String token = jwtService.createToken(claims, userLogin.getEmail());
            Map<String,Object> data = new HashMap<>();
            data.put("token", token);
            return responseHandler.successResponse(
                data,
                LoginConstants.SUCCESS_MESSAGES.get("LOGIN_USER"),
                200
            );
        } catch (Exception error) {
            return responseHandler.errorResponse(
                error,
                LoginConstants.ERROR_MESSAGES.get("LOGIN_USER"),
                500
            );
        }
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> userRegistration(@Valid @RequestBody UserRegistration userRegistration) {
        try {
            System.out.println(userRegistration);
            Map<String, Object> data = new HashMap<>();
            loginService.registerUser(userRegistration);
            return responseHandler.successResponse(
                data,
                LoginConstants.SUCCESS_MESSAGES.get("REGISTER_USER"),
                200
            );
        } catch (Exception error) {
            return responseHandler.errorResponse(
                error,
                LoginConstants.ERROR_MESSAGES.get("REGISTER_USER"),
                500
            );
        }
    }
    
}
