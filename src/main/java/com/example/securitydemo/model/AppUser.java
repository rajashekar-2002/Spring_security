package com.example.securitydemo.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "users")
public class AppUser {

    @Id
    private String id;

    @Indexed(unique = true) // Prevent duplicate usernames
    private String username;

    private String password; // Encrypted password

    private Set<String> roles; // Example: ["USER"], ["ADMIN"]
}