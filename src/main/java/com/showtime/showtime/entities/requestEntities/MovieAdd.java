package com.showtime.showtime.entities.requestEntities;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotNull;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Component
@Data
public class MovieAdd {
    @NotNull(message = "Title cannot be empty")
    private String title;
    private String description;
    private String genre;
    @NotNull(message = "Release date cannot be empty")
    private Date releaseDate;
    @NotNull
    private Long duration;
    @Size(min = 1, message = "Please add atleast one actor")
    private List<ObjectId> actorIds;
    private boolean isAvailableToBook;
}
