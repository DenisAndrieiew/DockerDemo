package com.andrieiew.docker.demo.controller;

import com.andrieiew.docker.demo.users.User;
import com.andrieiew.docker.demo.users.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class MainController {
    private final UserService userService;

    @GetMapping("/getAllUsers")
    public List<User> getListOfAllUsers() {
        System.out.println("userService.findAll() = " + userService.findAll());
        return userService.findAll();
    }

    @PostMapping(value = "addNewUser")
    public void addNewUser(@RequestBody(required = false) User newUser) {
        userService.save(newUser);
    }
}
