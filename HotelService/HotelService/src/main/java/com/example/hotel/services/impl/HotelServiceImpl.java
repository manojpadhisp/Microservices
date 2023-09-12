package com.example.hotel.services.impl;

import com.example.hotel.entities.Hotel;
import com.example.hotel.exception.ResourceNotFoundException;
import com.example.hotel.repositories.HotelRepositry;
import com.example.hotel.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepositry hotelRepositry;
    @Override
    public Hotel create(Hotel hotel) {
        String hotelId=UUID.randomUUID().toString();
        hotel.setId(hotelId);
        return hotelRepositry.save(hotel);
    }

    @Override
    public List<Hotel> getAll() {
        return hotelRepositry.findAll();
    }

    @Override
    public Hotel get(String id) {
        return hotelRepositry.findById(id).orElseThrow(()->new ResourceNotFoundException("Hotel given id not found") );
    }
}
