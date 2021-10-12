package com.kam.springboot3.controllers;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ThirdController {

    @GetMapping("/thirdController")
    public ResponseEntity<String> getThirdController()
    {
        return ResponseEntity.of(Optional.of("Hello Third Service - third controller"));
    }
}