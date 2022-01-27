package com.example.blog.repository;

import com.example.blog.model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CommentRepository extends MongoRepository<Comment,Long> {
}
