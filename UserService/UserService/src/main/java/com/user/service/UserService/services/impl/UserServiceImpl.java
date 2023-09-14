package com.user.service.UserService.services.impl;

import com.user.service.UserService.Exception.ResourceNotFoundException;
import com.user.service.UserService.entities.Hotel;
import com.user.service.UserService.entities.Rating;
import com.user.service.UserService.entities.User;
import com.user.service.UserService.externalservice.HotelServie;
import com.user.service.UserService.repositories.UserRepository;
import com.user.service.UserService.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    //communication with other service
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelServie hotelServie;

    private Logger logger= (Logger) LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
       String randomUserid =UUID.randomUUID().toString();
       user.setUserId(randomUserid);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getuser(String userid) {
        User user= userRepository.findById(userid).orElseThrow(()-> new ResourceNotFoundException("User with given id is not found on server:"+ userid));
        //fetch rating from user service.
       Rating[] ratingofuser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(), Rating[].class);
       logger.info( "{}",ratingofuser);

        List<Rating> ratings= Arrays.stream(ratingofuser).toList();

        List<Rating> ratingList=ratings.stream().map(rating -> {

         //Below one line code not need due to feign client call
         //ResponseEntity<Hotel> forEntity=restTemplate.getForEntity("http://HOTEL_SERVICE/hotels/"+rating.getHotelId(), Hotel.class);

         Hotel hotel=hotelServie.getHotel(rating.getHotelId());
        // logger.info("Response status code: {} ",forEntity.getStatusCode());
         rating.setHotel(hotel);
         return rating;
        }).collect(Collectors.toList());


       user.setRatingList(ratingList);
       return user;
    }
}
