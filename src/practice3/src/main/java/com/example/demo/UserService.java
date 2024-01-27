package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepositry userRepositry;

    public List<User> getAllUsers(){
        return userRepositry.findAll();
    }

    public User getUserByID(Long id){
        return userRepositry.findById(id);
    }
}
