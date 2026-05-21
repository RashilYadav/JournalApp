package com.Rashil.journalApp.controller;

import com.Rashil.journalApp.entity.User;
import com.Rashil.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController

// yeh class banana zaruri hai springboot ke application m
@RequestMapping("/public") // public se start hone wale endpoints humne authenticate nhi kiye hain toh isliye...postman m inko test rkte time hame Authentication m "username" aur "password" dene ki need nhi hogi
public class PublicController {
    @Autowired
    private UserService userService;

    // isme woh endpoints hain jo public rahenge...like user create krna ya user get krna ..jiske liye login ki need na ho
    // inn endpoints se agar user create ya get kr rhe toh "Authentication" mai "Basic Auth" select krke username, password dene ki need nhi hai
    @GetMapping("/health-check")
    public String healthCheck() {
        return "Ok";
    }
    @PostMapping("/create-user")
    public void createUser(@RequestBody User user) {
        userService.saveNewUser(user);
    }
}
