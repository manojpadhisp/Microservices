package com.user.service.UserService.services;

import com.user.service.UserService.entities.User;

import java.util.List;

public interface UserService {

    //Create
    User saveUser(User user);

    //get all user

    List<User> getAllUser();

    //get single user of given userid

    User getuser(String userid);



}
