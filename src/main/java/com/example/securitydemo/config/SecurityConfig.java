package com.example.securitydemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // Marks this as configuration class
@EnableMethodSecurity // Enables method-level security like @PreAuthorize
public class SecurityConfig {

    // 🔑 Defines security rules for HTTP requests
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                // Disable CSRF for simplicity (important for APIs)
                .csrf(csrf -> csrf.disable())

                // Define authorization rules
                .authorizeHttpRequests(auth -> auth

                        // Public endpoint (no authentication required)
                        .requestMatchers("/public/**").permitAll()

                        // Only users with ROLE_USER can access /user/**
                        .requestMatchers("/user/**").hasRole("USER")

                        // Only users with ROLE_ADMIN can access /admin/**
                        .requestMatchers("/admin/**").hasRole("ADMIN")

                        // Any other request must be authenticated
                        .anyRequest().authenticated())

                // Enable HTTP Basic Authentication
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    // 👤 Create in-memory users (no database for now)
    @Bean
    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {

        // USER role account
        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder.encode("password")) // encrypt password
                .roles("USER") // ROLE_USER
                .build();

        // ADMIN role account
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin123"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

    // 🔒 Password encoder bean (required!)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Strong hashing algorithm
    }
}