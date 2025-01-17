package com.showtime.showtime.entities.responseEntities;
import lombok.Data;

@Data
public class MoviesWithActiveBooking {
    private String id;
    private String title;
    public MoviesWithActiveBooking(String id, String title) {
        this.id = id;
        this.title = title;
    }
}
