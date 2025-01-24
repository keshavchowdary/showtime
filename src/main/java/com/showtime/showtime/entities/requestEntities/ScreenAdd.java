package com.showtime.showtime.entities.requestEntities;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class ScreenAdd {
    private String name;
    private String location;
}
