package com.showtime.showtime.entities;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.showtime.showtime.utils.enums.GenderEnum;

import lombok.Data;

@Document(collection = "users")
@Data
public class User {
    @Id
    private ObjectId id;
    @Indexed(unique = true)
    private String email;
    private String password;
    private String name;
    private Integer age;
    private GenderEnum gender;
    @CreatedDate
    private Date createdDate;
    @LastModifiedDate
    private Date updatedDate;
}
