package com.showtime.showtime.services;

import org.springframework.data.mongodb.core.query.Query;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;

import com.showtime.showtime.entities.requestEntities.UserLogin;
import com.showtime.showtime.entities.requestEntities.UserRegistration;
import com.showtime.showtime.models.User;
import com.showtime.showtime.utils.MongoHelper;

@Component
public class LoginService {
    
    @Autowired
    private MongoHelper mongoHelper;
    @Autowired
    private UserService userService;
    @Autowired
    private JWTService jwtService;

    public String userLogin(UserLogin userLogin) {
        User userDetail = userService.getUserDetails(null, userLogin.getEmail());
        if (userDetail == null) {
            throw new RuntimeException("User not found. Please register");
        }
        if (!userDetail.getPassword().equals(userLogin.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        Map<String, Object> claims = new HashMap<>();
        if (userDetail.getEmail() != null) {
            claims.put("email", userDetail.getEmail());
        }
        if (userDetail.getId() != null) {
            claims.put("userId", userDetail.getId().toString());
        }
        if (userDetail.getName() != null) {
            claims.put("name", userDetail.getName());
        }
        String token = jwtService.createToken(claims, userLogin.getEmail());
        return token;
    }

    public void registerUser(UserRegistration userRegistration) {
        Query userCountQuery = new Query();
        userCountQuery.addCriteria(Criteria.where("email").is(userRegistration.getEmail()));
        int count = mongoHelper.count(userCountQuery, User.class);
        if (count > 0) {
            throw new RuntimeException("User already exists");
        }
        User user = new User();
        user.setEmail(userRegistration.getEmail());
        user.setPassword(userRegistration.getPassword());
        user.setName(userRegistration.getName());
        user.setAge(userRegistration.getAge());
        user.setGender(userRegistration.getGender());
        mongoHelper.save(user);
    }
}
