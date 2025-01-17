package com.showtime.showtime.entities.requestEntities;
import org.springframework.stereotype.Component;

import com.showtime.showtime.enums.GenderEnum;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Component
@Data
public class ActorAdd {
    @Size(min = 3, max = 50)
    private String name;
    private GenderEnum gender;
}
