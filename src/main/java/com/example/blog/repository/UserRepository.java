package com.example.blog.repository;

import com.example.blog.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, ObjectId> {
    Optional<User> findById(ObjectId id);
    Optional<User> findUserByEmail(String email);
}
