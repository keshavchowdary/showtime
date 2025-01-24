package com.showtime.showtime.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.showtime.showtime.entities.requestEntities.ShowAdd;
import com.showtime.showtime.models.Show;
import com.showtime.showtime.utils.MongoHelper;

@Component
public class BookingService {

    @Autowired
    private MongoHelper mongoHelper;


}
