package com.example.blog.service.impl;

import com.example.blog.repository.PostRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostServiceImplTest {
    @Autowired
    private PostRepository postRepository;

    @Test
    void testGetOneRepositoryMethod(){


    }
}