package com.showtime.showtime.models;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import com.showtime.showtime.enums.GenderEnum;

import lombok.Data;

@Document(collection = "actors")
@Data
public class Actor {
    @Id
    private ObjectId id;
    private String name;
    private GenderEnum gender;
    @CreatedDate
    private Date createdDate;
    @LastModifiedDate
    private Date updatedDate;
}
