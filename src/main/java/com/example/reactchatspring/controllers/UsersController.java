package com.example.reactchatspring.controllers;

import com.example.reactchatspring.models.User;
import com.example.reactchatspring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
public class UsersController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public Iterable<User> findAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{userId}")
    public Optional<User> findUserById(@PathVariable Long userId) {
        return userRepository.findById(userId);
    }

    @DeleteMapping("/users/{userId}")
    public HttpStatus deleteUserById(@PathVariable Long userId) {
        userRepository.deleteById(userId);
        return HttpStatus.OK;
    }

    @GetMapping("/users/name/{username}")
    public Optional<User> findByUsername(@PathVariable String username) {
        return userRepository.findByUsername(username);
    }

    @PostMapping("/users")
    public User createNewUser(@RequestBody User newUser) {
        return userRepository.save(newUser);
    }

    @PatchMapping("/users/{userId}")
    public User updateUserById(@PathVariable Long userId, @RequestBody User userRequest) {

        User userFromDb = userRepository.findById(userId).get();
        userFromDb.setUsername(userRequest.getUsername());
        userFromDb.setPassword(userRequest.getPassword());
        return userRepository.save(userFromDb);
    }
    @DeleteMapping("/users/name/{username}")
    public HttpStatus deleteByUsername(@PathVariable String username) {
        userRepository.deleteByUsername(username);
        return HttpStatus.OK;
    }

    @PatchMapping("/users/name/{username}")
    public User updateByUsername(@RequestBody User userRequest, @PathVariable String username){
        User userFromDb = userRepository.findByUsername(username).get();
        userFromDb.setUsername(userRequest.getUsername());
//        userFromDb.setPassword(userRequest.getPassword());
        return userRepository.save(userFromDb);
    }
}