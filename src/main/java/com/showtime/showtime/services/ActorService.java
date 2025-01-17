package com.showtime.showtime.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.showtime.showtime.entities.requestEntities.ActorAdd;
import com.showtime.showtime.models.Actor;
import com.showtime.showtime.utils.MongoHelper;

@Component
public class ActorService {

    @Autowired
    private MongoHelper mongoHelper;

    public void addNewActor(ActorAdd newActor) {
        Actor actor = new Actor();
        actor.setName(newActor.getName());
        actor.setGender(newActor.getGender());
        mongoHelper.save(actor);
    }
}
