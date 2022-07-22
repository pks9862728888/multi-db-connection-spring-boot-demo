package com.demo.multidbconnectionspringbootdemo.controllers;

import com.demo.multidbconnectionspringbootdemo.db1.models.User;
import com.demo.multidbconnectionspringbootdemo.db1.services.Db1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/db1")
public class Db1Controller extends GenericExceptionHandlerController {

    @Autowired
    private Db1Service db1Service;

    @GetMapping("/create")
    public ResponseEntity<User> createNewUser() throws Exception {
        int userId = db1Service.createUser(User.builder()
                .name("user1")
                .build()).getId();
        Optional<User> user = db1Service.findUserBy(userId);
        if (user.isEmpty()) {
            throw new Exception("No user found having userId: " + userId);
        } else {
            return new ResponseEntity<>(user.get(), HttpStatus.CREATED);
        }
    }

}
