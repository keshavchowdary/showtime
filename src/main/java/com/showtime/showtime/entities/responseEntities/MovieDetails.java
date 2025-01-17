package com.showtime.showtime.entities.responseEntities;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.showtime.showtime.config.serializers.ObjectToStringSerializer;

import lombok.Data;

@Data
class ActorDetails {
    @JsonSerialize(using = ObjectToStringSerializer.class)
    private ObjectId id;
    private String name;
    public ActorDetails(ObjectId id, String name) {
        this.id = id;
        this.name = name;
    }
}

@Data
public class MovieDetails {
    @JsonSerialize(using = ObjectToStringSerializer.class)
    private ObjectId id;
    private String title;
    private String description;
    private String genre;
    private Date releaseDate;
    private Long duration;
    private List<ActorDetails> actors;
    @JsonProperty("isAvailableToBook")
    private boolean isAvailableToBook;
}
