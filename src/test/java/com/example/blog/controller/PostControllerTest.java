package com.example.blog.controller;

import com.example.blog.model.Post;
import com.example.blog.repository.PostRepository;
import com.example.blog.service.UserService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostControllerTest {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserService userService;

    @Test
    public void getPostByIdTest(){


        Post post = postRepository.findById(new ObjectId("61fdfcd31b43341b16e00be7")).get();
        System.out.println(post);
    }

    @Test
    public void getUserById(){
        String author = userService.getUserById(new ObjectId("622572c7e5be7ff27398de7f")).get().getEmail();
        System.out.println(author);
    }

}