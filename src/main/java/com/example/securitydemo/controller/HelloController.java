package com.example.securitydemo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    @GetMapping("/public/hello")
    public String publicHello() {
        return "Public Endpoint";
    }

    @GetMapping("/user/hello")
    public String userHello() {
        return "User Endpoint";
    }

    @GetMapping("/admin/hello")
    public String adminHello() {
        return "Admin Endpoint";
    }
}