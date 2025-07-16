package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.model.UserRole;

import java.util.Set;

public interface UserService {

    //creating user
    public User createUser(User user, Set<UserRole> userRoles) throws Exception;
}
