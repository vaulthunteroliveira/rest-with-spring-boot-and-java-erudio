package com.example.restwithspringbootandjavaerudio.controller;

import com.example.restwithspringbootandjavaerudio.model.User;
import com.example.restwithspringbootandjavaerudio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/v1")
public class UserController {

    @Autowired
    private UserService userService;

//    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
//    public User findUserByUsername(@RequestParam(name = "username") String username){
//        return userService.findByUsername(username);
//    }
}
