package com.showtime.showtime.entities.requestEntities;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Component
@Data
public class UserLogin {
    @Email(message = "Email should be valid")
    private String email;
    private String password;
}
