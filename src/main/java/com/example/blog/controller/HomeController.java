package com.example.blog.controller;

import com.example.blog.dto.PostInfoDto;
import com.example.blog.model.Post;
import com.example.blog.model.User;
import com.example.blog.service.PostService;
import com.example.blog.service.UserService;
import com.example.blog.util.Pager;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(value="/home")
    public String index(@RequestParam(defaultValue = "0") int page, Model model){
        System.out.println("home page");
        Page<PostInfoDto> posts = postService.findAllOrderByDatePageable(page).map((post)-> convertToPostInfoDto(post,post.getUserId()));
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails)principal).getUsername();
        } else {
            String username = principal.toString();
        }

        //return postInfoDto;
        Pager pager = new Pager(posts);
        model.addAttribute("pager",pager);
        return "/home";
    }

    private PostInfoDto convertToPostInfoDto(Post post, ObjectId id){
        PostInfoDto postInfoDto = modelMapper.map(post,PostInfoDto.class);
        String author = userService.getUserById(id).get().getEmail();
        postInfoDto.setAuthor(author);
        return postInfoDto;
    }
}
