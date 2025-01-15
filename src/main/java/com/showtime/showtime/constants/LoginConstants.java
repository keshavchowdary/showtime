package com.showtime.showtime.constants;

import java.util.Map;

import org.springframework.stereotype.Component;

import static java.util.Map.entry; 
//static import allows you to directly use static method without mentioning the class name

@Component
public class LoginConstants {
    public static final Map<String, String> SUCCESS_MESSAGES = Map.ofEntries(
        entry("LOGIN_USER", "Login successful"),
        entry("REGISTER_USER", "Registration successful")
    );
    public static final Map<String, String> ERROR_MESSAGES = Map.ofEntries(
        entry("LOGIN_USER", "Login Failed"),
        entry("REGISTER_USER", "Registration Failed")
    );
}
