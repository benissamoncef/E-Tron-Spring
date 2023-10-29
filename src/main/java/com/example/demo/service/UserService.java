package com.example.demo.service;

import java.util.List;

import com.example.demo.model.User;
import com.example.demo.model.UserDto;


public interface UserService {
    User save(UserDto user);
    List<User> findAll();
    User findOne(String username);
}