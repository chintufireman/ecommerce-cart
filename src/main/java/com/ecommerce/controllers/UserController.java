package com.ecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ecommerce.services.UserService;


import com.ecommerce.payloads.*;
@RestController
@RequestMapping("/api/v1/user/service")
public class UserController {
    
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user){
        UserDto createdUser = this.userService.createUser(user);
        return new ResponseEntity<UserDto>(createdUser,HttpStatus.CREATED);
    }

    @GetMapping("/get/{userid}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer userid){
        UserDto user = this.userService.getUserById(userid);
        return new ResponseEntity<UserDto>(user,HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> allUsers = this.userService.getAllUsers();
        return ResponseEntity.ok(allUsers);
    }
}
