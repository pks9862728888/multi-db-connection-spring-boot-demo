package com.demo.multidbconnectionspringbootdemo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GenericExceptionHandlerController {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity<>(
                String.format("%s exception occurred, message: %s",
                        e.getClass().getSimpleName(), e.getMessage()),
                        HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
