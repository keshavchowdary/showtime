package com.showtime.showtime.services;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.showtime.showtime.entities.UserAuthData;
import com.showtime.showtime.models.User;
import com.showtime.showtime.utils.MongoHelper;

@Service
public class UserService {

    @Autowired
    private MongoHelper mongoHelper;

    public List<User> getAllUsers() {
        return new ArrayList<>();
    }

    public User getUserDetails(ObjectId userId, String email) {
        Query query = new Query();
        if (userId != null) {
            query.addCriteria(Criteria.where("userId").is(userId));
            return (User) mongoHelper.findOne(query, User.class);
        } else if (email != null) {
            query.addCriteria(Criteria.where("email").is(email));
            return (User) mongoHelper.findOne(query, User.class);
        } else {
            throw new RuntimeException("Invalid request");
        }
    }

    public UserAuthData getUserDetailsFromAuth() {
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        return (UserAuthData) authentication.getPrincipal();
    }

    public ObjectId getUserId() {
        UserAuthData userData = getUserDetailsFromAuth();
        return userData.getUserId();
    }
}
