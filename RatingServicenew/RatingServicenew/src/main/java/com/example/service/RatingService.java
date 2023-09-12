package com.example.service;

import com.example.entities.Rating;

import java.util.List;

public interface RatingService {

    Rating create(Rating rating);

    List<Rating> getRating();
    List<Rating> getRatingByUserId(String userId);
    List<Rating> getratingByHotelId(String hotelId);
}
