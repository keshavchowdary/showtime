package com.showtime.showtime.models;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "shows")
@Data
public class Show {
    @Id
    private ObjectId id;
    private ObjectId movieId;
    private ObjectId screenId;
    private Date startTime;
    private Date endTime;
    @CreatedDate
    private Date createdDate;
    @LastModifiedDate
    private Date updatedDate;
}
