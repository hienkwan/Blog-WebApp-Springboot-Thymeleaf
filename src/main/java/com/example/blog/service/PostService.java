package com.example.blog.service;

import com.example.blog.model.Post;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PostService {
    Page<Post> findAllOrderByDatePageable(int page);
}
