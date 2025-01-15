package com.showtime.showtime.entities;

import org.bson.types.ObjectId;

import lombok.Data;

@Data
public class UserAuthData {
    private String email;
    private String name;
    private ObjectId userId;
    public UserAuthData(String email, String name, ObjectId userId) {
        this.email = email;
        this.name = name;
        this.userId = userId;
    }
}
