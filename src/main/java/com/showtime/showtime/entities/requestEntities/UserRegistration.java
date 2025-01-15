package com.showtime.showtime.entities.requestEntities;

import org.springframework.stereotype.Component;

import com.showtime.showtime.enums.GenderEnum;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Component
@Data
public class UserRegistration {
    @Email(message = "Email should be valid")
    private String email;
    private String password;
    @Size(min = 3, max = 50, message = "Name should be between 3 and 50 characters")
    private String name;
    @Min(value = 1, message = "Age should be greater than 0")
    @Max(value = 100, message = "Age should be less than 100")
    private Integer age;
    private GenderEnum gender;
}
