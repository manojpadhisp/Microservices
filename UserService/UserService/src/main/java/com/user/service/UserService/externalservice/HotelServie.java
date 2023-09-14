package com.user.service.UserService.externalservice;

import com.user.service.UserService.entities.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "HOTEL-SERVICE")
public interface HotelServie
{

    @GetMapping("/hotels/{hotelId}")
    Hotel getHotel(@PathVariable  String hotelId);

}
