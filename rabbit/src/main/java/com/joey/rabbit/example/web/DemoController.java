package com.joey.rabbit.example.web;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DemoController {


    @RequestMapping("/")
    public String getStart() {


        return "Hello";
    }


}

