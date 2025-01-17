package com.showtime.showtime.controllers;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.showtime.showtime.entities.requestEntities.ActorAdd;
import com.showtime.showtime.services.ActorService;
import com.showtime.showtime.utils.ApiResponse;
import com.showtime.showtime.utils.ResponseHandler;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin/actor")
public class ActorController {

    @Autowired
    private ActorService actorService;
    @Autowired
    private ResponseHandler responseHandler;

    @PostMapping("/new")
    public ResponseEntity<ApiResponse> addNewActor(@Valid @RequestBody ActorAdd newActor) {
        try {
            actorService.addNewActor(newActor);
            return responseHandler.successResponse(Collections.emptyMap(), "Successfully added actor", 200);
        } catch (Exception error) {
            return responseHandler.errorResponse(error, "Failed to add actor", 500);
        }
    }
    
}
