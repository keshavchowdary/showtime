package com.showtime.showtime.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.showtime.showtime.entities.requestEntities.ScreenAdd;
import com.showtime.showtime.models.Screen;
import com.showtime.showtime.utils.MongoHelper;

@Component
public class ScreenService {

    @Autowired
    private MongoHelper mongoHelper;

    public void addScreen(ScreenAdd screenAdd) {
        Screen screen = new Screen();
        screen.setName(screenAdd.getName());
        screen.setLocation(screenAdd.getLocation());
        mongoHelper.save(screen);
    }
}
