package com.example.blog.service.impl;

import com.example.blog.model.Post;
import com.example.blog.repository.PostRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.example.blog.service.PostService;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;


    @Override
    public Page<Post> findAllOrderByDatePageable(int page) {
        return postRepository.findAllByOrderByCreatedDateDesc(PageRequest.of(subtractPageByOne(page),5));
    }

    @Override
    public Optional<Post> findPostById(ObjectId id) {
        return postRepository.findById(id);
    }

    private int subtractPageByOne(int page){
        return (page < 1) ? 0 : page - 1;
    }
}
