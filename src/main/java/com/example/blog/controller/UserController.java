package com.example.blog.controller;

import com.example.blog.model.User;
import com.example.blog.repository.UserRepository;
import com.example.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value="/user",method = RequestMethod.GET)
    @ResponseBody
    public List<User> getAllUsers(){
        System.out.println("getAllUsers call");
        return userService.getUsers();
    }

}
