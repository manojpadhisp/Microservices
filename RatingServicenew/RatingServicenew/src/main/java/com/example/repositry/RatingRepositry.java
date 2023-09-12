package com.example.repositry;

import com.example.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepositry extends JpaRepository<Rating,String> {
    List<Rating> findByUserId(String userId);

    List<Rating> findByHotelId(String hotel);
}
