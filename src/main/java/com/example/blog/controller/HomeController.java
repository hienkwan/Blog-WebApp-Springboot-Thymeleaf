package com.example.blog.controller;

import com.example.blog.model.Post;
import com.example.blog.model.User;
import com.example.blog.service.PostService;
import com.example.blog.util.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private PostService postService;

    @GetMapping(value="/home")
    public String index(@RequestParam(defaultValue = "0") int page, Model model){
        System.out.println("home page");
        Page<Post> posts = postService.findAllOrderByDatePageable(page);
        Pager pager = new Pager(posts);
        model.addAttribute("pager",pager);
        return "/home";
    }
}
