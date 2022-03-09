package com.example.blog.controller;

import com.example.blog.model.User;
import com.example.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/login")
    public String login() {
        System.out.println("aaaaaaaaaaaaaaaa");
        return "/home";
    }

    @GetMapping(value = "/login")
    public String loginPage(){
        return "/login";
    }

    @GetMapping(value = "/logout")
    public String logout(){
        return "/login";
    }

    @PostMapping(value = "/signup", consumes = {"application/json"})
    public String signUp(@RequestBody User user){
        userService.signUpUser(user);
        return "redirect:login";
    }
}
