package com.example.service.impl;

import com.example.entities.Rating;
import com.example.repositry.RatingRepositry;
import com.example.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepositry ratingRepositry;
    @Override
    public Rating create(Rating rating) {
        String ratingId= UUID.randomUUID().toString();
        rating.setRatingId(ratingId);
        return ratingRepositry.save(rating);
    }

    @Override
    public List<Rating> getRating() {
        return ratingRepositry.findAll();
    }

    @Override
    public List<Rating> getRatingByUserId(String userId) {
        return ratingRepositry.findByUserId(userId);
    }

    @Override
    public List<Rating> getratingByHotelId(String hotelId) {
        return ratingRepositry.findByHotelId(hotelId);
    }

}
