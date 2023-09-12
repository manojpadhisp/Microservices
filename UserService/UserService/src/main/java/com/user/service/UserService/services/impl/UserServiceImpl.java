package com.user.service.UserService.services.impl;

import com.user.service.UserService.Exception.ResourceNotFoundException;
import com.user.service.UserService.entities.User;
import com.user.service.UserService.repositories.UserRepository;
import com.user.service.UserService.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

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
        return userRepository.findById(userid).orElseThrow(()-> new ResourceNotFoundException("User with given id is not found on server:"+ userid));
    }
}
