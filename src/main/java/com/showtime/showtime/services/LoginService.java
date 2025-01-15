package com.showtime.showtime.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.showtime.showtime.entities.requestEntities.UserRegistration;
import com.showtime.showtime.models.User;
import com.showtime.showtime.utils.MongoHelper;

@Component
public class LoginService {
    
    @Autowired
    private MongoHelper mongoHelper;

    public void registerUser(UserRegistration userRegistration) {
        User user = new User();
        user.setEmail(userRegistration.getEmail());
        user.setPassword(userRegistration.getPassword());
        user.setName(userRegistration.getName());
        user.setAge(userRegistration.getAge());
        user.setGender(userRegistration.getGender());
        mongoHelper.save(user);
    }
}
