package com.example.blog.controller;

import com.example.blog.dto.PostInfoDto;
import com.example.blog.model.Post;
import com.example.blog.service.PostService;
import com.example.blog.service.UserService;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(value="/post/{id}")
    public String findPostById(@PathVariable String id, Model model){
        ObjectId idPass = new ObjectId(id);
        Post post = postService.findPostById(idPass).get();
        model.addAttribute("post",post);
        System.out.println("Post page");
        return "/post";
    }

    @GetMapping(value="/management/posts")
    public String getAllPosts(Model model) {
        List<PostInfoDto> post = postService.getAllPosts().stream()
                .map(postMap->convertToPostInfoDto(postMap,postMap.getUserId()))
                .collect(Collectors.toList());
        model.addAttribute("posts",post);
        return "/admin/posts";
    }

    private PostInfoDto convertToPostInfoDto(Post post, ObjectId id){
        PostInfoDto postInfoDto = modelMapper.map(post,PostInfoDto.class);
        String author = userService.getUserById(id).get().getEmail();
        postInfoDto.setAuthor(author);
        return postInfoDto;
    }


    }
