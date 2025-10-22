package com.example.myfirstproject.controller;

import com.example.myfirstproject.model.User;
import com.example.myfirstproject.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class TestController {
    final UserService userService;
    public TestController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("public")
    public String publicMethod(){
        return "public";
    }

    @GetMapping("admin")
    public String adminMethod(){
        return "admin";
    }

    @GetMapping("user")
    public String userMethod(){
        return "user";
    }

    @PostMapping("register")
    public User register(@RequestBody User user){
        return userService.addUser(user);
    }
}
