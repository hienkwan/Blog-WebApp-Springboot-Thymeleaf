package com.example.blog.controller;

import com.example.blog.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class HomeController {

    @RequestMapping(value="/",method = RequestMethod.GET)
    public String index(){
        System.out.println("index page");
        return "home";
    }
}
