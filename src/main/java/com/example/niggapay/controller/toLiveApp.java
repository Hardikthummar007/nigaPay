package com.example.niggapay.controller;


import jakarta.persistence.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("live")


public class toLiveApp {

    @Autowired
    RestTemplate restTemplate;


    @GetMapping("test")
    public ResponseEntity<?> live(){


        System.out.println("live dude");

        return new ResponseEntity<>("live", HttpStatus.OK);
    }
}
