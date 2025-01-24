package com.showtime.showtime.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.showtime.showtime.services.BookingService;
import com.showtime.showtime.utils.ResponseHandler;

@RestController
public class BookingController {
    
    @Autowired
    private ResponseHandler responseHandler;
    @Autowired
    private BookingService bookingService;

}
