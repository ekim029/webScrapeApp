package com.eric.job_scraper.service.core;

import com.eric.job_scraper.model.User;
import com.eric.job_scraper.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
// Used to mark a class as a service component in the Spring framework.
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    // Used to automatically inject dependencies (of dependencies) into your class.
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User updateUser(Long id, User user) {
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser == null) {
            return null;
        }
        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());
        return userRepository.save(existingUser);
    }

    public boolean deleteUser(Long id) {
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser == null) {
            return false;
        }
        userRepository.deleteById(id);
        return true;
    }

}
