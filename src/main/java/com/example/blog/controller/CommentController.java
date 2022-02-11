package com.example.blog.controller;

import com.example.blog.model.Comment;
import com.example.blog.model.Post;
import com.example.blog.service.PostService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class CommentController {
    @Autowired
    private PostService postService;

    @PostMapping(value="/post/comment/{id}")
    public ResponseEntity findPostById(@PathVariable String id, @RequestBody Comment comment){
        ObjectId objectId = new ObjectId(id);
        Post post = postService.addCommentByPostId(comment,objectId);
        if(post!=null){
            return ResponseEntity.status(HttpStatus.CREATED).body("Comment add sucessfull");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comment add fail");
        }
    }
}
