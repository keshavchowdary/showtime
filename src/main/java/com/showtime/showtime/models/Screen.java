package com.showtime.showtime.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "screens")
@Data
public class Screen {
    @Id
    private ObjectId id;
    private String name;
    private String location;
}
