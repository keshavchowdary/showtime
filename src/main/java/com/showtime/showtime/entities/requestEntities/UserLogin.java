package com.showtime.showtime.entities.requestEntities;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class UserLogin {
    private String email;
    private String password;
}
