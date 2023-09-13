package com.user.service.UserService.services.impl;

import com.user.service.UserService.Exception.ResourceNotFoundException;
import com.user.service.UserService.entities.Rating;
import com.user.service.UserService.entities.User;
import com.user.service.UserService.repositories.UserRepository;
import com.user.service.UserService.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    //communication with other service
    @Autowired
    private RestTemplate restTemplate;

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
       ArrayList<Rating> forobject = restTemplate.getForObject("http://localhost:8083/ratings/users/"+user.getUserId(), ArrayList.class);
       logger.info( "{}",forobject);
       user.setRatingList(forobject);
       return user;
    }
}
