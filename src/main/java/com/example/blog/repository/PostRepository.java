package com.example.blog.repository;

import com.example.blog.model.Post;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends MongoRepository<Post,ObjectId> {
    Page<Post> findAllByOrderByCreatedDateDesc(Pageable pageable);

    @Override
    Optional<Post> findById(ObjectId id);

    void deletePostBy_id(ObjectId id);

}
