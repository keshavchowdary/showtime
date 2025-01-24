package com.showtime.showtime.controllers;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.showtime.showtime.entities.requestEntities.ShowAdd;
import com.showtime.showtime.services.ShowsService;
import com.showtime.showtime.utils.ApiResponse;
import com.showtime.showtime.utils.ResponseHandler;

@RestController
@RequestMapping("/admin/shows")
public class ShowsController {

    @Autowired
    private ShowsService showsService;
    @Autowired
    private ResponseHandler responseHandler;
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addShow(@RequestBody ShowAdd showAdd) {
        try {
            showsService.addNewShow(showAdd);
            return responseHandler.successResponse(Collections.emptyMap(), "Successfully added a show", 200);
        } catch(Exception error) {
            return responseHandler.errorResponse(error, "Failed to add show. Please try again.", 404);
        }
    }
}
