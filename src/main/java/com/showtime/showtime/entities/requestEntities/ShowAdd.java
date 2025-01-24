package com.showtime.showtime.entities.requestEntities;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class ShowAdd {
    private ObjectId screenId;
    private ObjectId movieId;
    private Date startTime;
    private Date endTime;
}
