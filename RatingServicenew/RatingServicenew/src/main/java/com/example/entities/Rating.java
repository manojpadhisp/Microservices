package com.example.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name= "user_ratings")
public class Rating {

    @Id
    private String ratingId;

    private String userId;
    private String hotelId;
    private int rating;
    private String feedback;
}
