package com.eric.job_scraper.controller;

import com.eric.job_scraper.model.User;
import com.eric.job_scraper.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
// You use @RestController when your class will handle HTTP requests and return JSON or other data in the response body.

@RequestMapping("/users")
// This annotation is used at the class level (or method level) to define the base URL path for your REST API.

public class UserController {

    private final UserService userService;

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

    @PutMapping("/{id}")
    // Shorthand versions of @RequestMapping for specific HTTP methods.
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        // Same annotations used in createUser & getUserById
        User updatedUser = userService.updateUser(id, user);
        if (updatedUser == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updatedUser);
        }
    }

    @DeleteMapping("/{id}")
    // Shorthand versions of @RequestMapping for specific HTTP methods.
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        // If you have a URL like /users/{id}, @PathVariable lets Spring Boot know that
        // the {id} part should be treated as a variable and passed to your method as a parameter.
        boolean userDeleted = userService.deleteUser(id);
        if (!userDeleted) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.noContent().build();
        }
    }

}
