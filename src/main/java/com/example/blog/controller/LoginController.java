package com.example.blog.controller;

import com.example.blog.model.Post;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

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
}
