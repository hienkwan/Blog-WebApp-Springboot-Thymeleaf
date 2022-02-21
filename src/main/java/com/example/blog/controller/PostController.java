package com.example.blog.controller;

import com.example.blog.dto.PostInfoDto;
import com.example.blog.exception.PostNotFoundException;
import com.example.blog.model.Post;
import com.example.blog.service.PostService;
import com.example.blog.service.UserService;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/post/{id}")
    public String findPostById(@PathVariable String id, Model model) {
        ObjectId idPass = new ObjectId(id);
        Post post = postService.findPostById(idPass).get();
        model.addAttribute("post", post);
        System.out.println("Post page");
        return "/post";
    }

    @GetMapping(value = "/management/posts")
    public String getAllPosts(Model model) {
        List<PostInfoDto> post = postService.getAllPosts().stream()
                .map(postMap -> convertToPostInfoDto(postMap, postMap.getUserId()))
                .collect(Collectors.toList());
        model.addAttribute("posts", post);
        return "/admin/posts";
    }

    @PutMapping(value = "/post/{id}")
    public ResponseEntity<String> updatePost(@PathVariable String id, @RequestBody Post postUpdate) {
        Post post;
        try {
            post = postService.updatePostById(id, postUpdate);
            return new ResponseEntity<>(post.toString(),HttpStatus.OK);
        } catch (PostNotFoundException e) {
            return new ResponseEntity<>(e.toString(),HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/post/{id}")
    public void deletePost(@PathVariable String id) {
        postService.deletePostById(id);
    }

    private PostInfoDto convertToPostInfoDto(Post post, ObjectId id) {
        PostInfoDto postInfoDto = modelMapper.map(post, PostInfoDto.class);
        String author = userService.getUserById(id).get().getEmail();
        postInfoDto.setAuthor(author);
        return postInfoDto;
    }


}
