package com.showtime.showtime.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.showtime.showtime.entities.User;

@Service
public class UserService {
    public List<User> getAllUsers() {
        return new ArrayList<>();
    }
}
