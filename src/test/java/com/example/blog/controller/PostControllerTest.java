package com.example.blog.controller;

import com.example.blog.model.Post;
import com.example.blog.repository.PostRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostControllerTest {
    @Autowired
    private PostRepository postRepository;

    @Test
    public void getPostByIdTest(){


        Post post = postRepository.findById(new ObjectId("61fdfcd31b43341b16e00be7")).get();
        System.out.println(post);
    }
}