package com.eric.job_scraper.controller;

import com.eric.job_scraper.model.User;
import com.eric.job_scraper.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
// You use @RestController when your class will handle HTTP requests and return JSON or other data in the response body.

@RequestMapping("/users")
// This annotation is used at the class level (or method level) to define the base URL path for your REST API.

public class UserController {

    private final UserService userService;

    @Autowired
    // Used to automatically inject dependencies (of dependencies) into your class.

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping
    // Shorthand versions of @RequestMapping for specific HTTP methods.
    public User createUser(@RequestBody User user) {
        // When a client sends data (like JSON) in the request body,
        // @RequestBody lets Spring Boot know that the body should be mapped to an object (like User).
        return userService.saveUser(user);
    }


    @GetMapping("/{id}")
    // Shorthand versions of @RequestMapping for specific HTTP methods.
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        // If you have a URL like /users/{id}, @PathVariable lets Spring Boot know that
        // the {id} part should be treated as a variable and passed to your method as a parameter.
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
            // ResponseEntity<User> with status 200 OK and the user data inside
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
