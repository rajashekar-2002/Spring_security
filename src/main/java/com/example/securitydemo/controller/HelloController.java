package com.example.securitydemo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    // No authentication required
    @GetMapping("/public/hello")
    public String publicHello() {
        return "Hello Public!";
    }

    // Requires USER role
    @GetMapping("/user/hello")
    public String userHello() {
        return "Hello User!";
    }

    // Requires ADMIN role
    @GetMapping("/admin/hello")
    public String adminHello() {
        return "Hello Admin!";
    }

    // Method-level security example
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/method-secured")
    public String methodSecured() {
        return "Only Admin via @PreAuthorize!";
    }
}