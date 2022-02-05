package com.example.blog.controller;

import com.example.blog.model.Post;
import com.example.blog.service.PostService;
import com.example.blog.util.Pager;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping(value="/post/{id}")
    public String findPostById(@PathVariable String id, Model model){
        ObjectId idPass = new ObjectId(id);
        Post post = postService.findPostById(idPass).get();
        model.addAttribute("post",post);
        return "/post";
    }
}
