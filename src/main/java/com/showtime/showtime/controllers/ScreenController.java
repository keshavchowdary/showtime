package com.showtime.showtime.controllers;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.showtime.showtime.entities.requestEntities.ScreenAdd;
import com.showtime.showtime.services.ScreenService;
import com.showtime.showtime.utils.ApiResponse;
import com.showtime.showtime.utils.ResponseHandler;

@RestController
@RequestMapping("/admin/screens")
public class ScreenController {
    
    @Autowired
    private ResponseHandler responseHandler;
    @Autowired
    private ScreenService screenService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addScreen(@RequestBody ScreenAdd screenAdd) {
        try {
            screenService.addScreen(screenAdd);
            return responseHandler.successResponse(Collections.emptyMap(), "Successfully added a screen", 200);
        } catch (Exception error) {
            return responseHandler.errorResponse(error, "Failed to add screen. Please try again", 404);
        }
    }
}
