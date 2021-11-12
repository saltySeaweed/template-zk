package com.example.zktraining.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class RestHomeController {
    @GetMapping(value = "index")
    public String home(){
        return "index";
    }
}
