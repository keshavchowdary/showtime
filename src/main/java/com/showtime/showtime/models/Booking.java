package com.showtime.showtime.models;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import com.showtime.showtime.enums.BookingStatusEnum;

import lombok.Data;

@Document(collection = "bookings")
@Data
public class Booking {
    @Id
    private ObjectId id;
    private ObjectId movieId;
    private ObjectId userId;
    private BookingStatusEnum status;
    private Date showTime;
    private ObjectId auditoriumId;
    private List<String> selectedSeats;
    private Double totalAmount;
    private byte userRating;
    @CreatedDate
    private Date createdDate;
    @LastModifiedDate
    private Date updatedDate;
}
