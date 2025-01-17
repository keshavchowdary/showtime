package com.showtime.showtime.models;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "movies")
@Data
public class Movie {
    @Id
    private ObjectId id;
    private String title;
    private String description;
    private String genre;
    private Date releaseDate;
    private Long duration;
    private List<ObjectId> actorIds;
    private boolean isAvailableToBook;
    @CreatedDate
    private Date createdDate;
    @LastModifiedDate
    private Date updatedDate;
}
