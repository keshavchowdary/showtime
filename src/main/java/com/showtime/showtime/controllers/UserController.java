package com.showtime.showtime.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.showtime.showtime.constants.UserConstants;
import com.showtime.showtime.entities.User;
import com.showtime.showtime.services.UserService;
import com.showtime.showtime.utils.ApiResponse;
import com.showtime.showtime.utils.ResponseHandler;

@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    private ResponseHandler responseHandler;
    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public ResponseEntity<ApiResponse> getAllUsers() {
        try {
            List<User> users = userService.getAllUsers();
            return responseHandler.successResponse(
                users,
                UserConstants.SUCCESS_MESSAGES.get("FETCH_ALL_USERS"),
                200
            );
        } catch (Exception error) {
            return responseHandler.errorResponse(
                error,
                UserConstants.ERROR_MESSAGES.get("FETCH_ALL_USERS"),
                500
            );
        }
    }
}
