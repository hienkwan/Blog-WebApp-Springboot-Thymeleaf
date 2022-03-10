package com.example.blog.controller;

import com.example.blog.exception.PostNotFoundException;
import com.example.blog.model.Comment;
import com.example.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommentController {
    @Autowired
    private PostService postService;

    @PostMapping(value = "/post/comment/{id}")
    public String addCommentByPostId(@PathVariable String id, @RequestParam String content,@RequestParam String commentName) {
        Comment comment = Comment.builder()
                .commentName(commentName)
                .content(content)
                .build();
        try {
            postService.addCommentByPostId(comment, id);
        } catch (PostNotFoundException e) {
            return null;
        }
        return "redirect:/post/"+id;

    }
}
