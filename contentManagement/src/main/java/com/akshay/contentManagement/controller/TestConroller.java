package com.akshay.contentManagement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/test")
public class TestConroller {
    @GetMapping()
    public String test(){
        return "connetion Sucessfull";
    }
}
