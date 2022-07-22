package com.demo.multidbconnectionspringbootdemo.db1.services;

import com.demo.multidbconnectionspringbootdemo.db1.models.User;
import com.demo.multidbconnectionspringbootdemo.db1.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class Db1Service {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findUserBy(int userId) {
        return userRepository.findById(userId);
    }
}
