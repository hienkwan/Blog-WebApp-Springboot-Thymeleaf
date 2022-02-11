package com.example.blog.service;

import com.example.blog.exception.PostNotFoundException;
import com.example.blog.model.Comment;
import com.example.blog.model.Post;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface PostService {
    Page<Post> findAllOrderByDatePageable(int page);

    Optional<Post> findPostById(ObjectId id);

    Post addCommentByPostId(Comment comment,String postId) throws PostNotFoundException;

}
