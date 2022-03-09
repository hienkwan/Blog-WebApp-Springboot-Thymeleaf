package com.example.blog.service;

import com.example.blog.model.User;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getUsers();
    Optional<User> getUserById(ObjectId id);
    User signUpUser(User user);
}
