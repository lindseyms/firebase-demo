package com.example.firebasedemo.controller;

import com.example.firebasedemo.model.User;
import com.example.firebasedemo.service.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
public class FirebaseController {

    private FirebaseService firebaseService;

    @Autowired
    public FirebaseController(FirebaseService firebaseService){
        this.firebaseService = firebaseService;
    }

    @PostMapping("/addUser")
    public String addUser(@RequestBody User user) throws ExecutionException, InterruptedException {
        firebaseService.saveData(user);
        return user.toString();
    }

    @GetMapping("/getUser")
    public String getUser() throws ExecutionException, InterruptedException {
        return firebaseService.fetchUser().toString();
    }

}
