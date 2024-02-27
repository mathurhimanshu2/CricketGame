package com.tekion.cricketgame.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {


    @GetMapping("/hello")
    public String simulateMatch(){
        return "Hello World";
    }
}
