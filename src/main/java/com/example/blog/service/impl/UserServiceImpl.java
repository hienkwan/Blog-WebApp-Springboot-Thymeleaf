package com.example.blog.service.impl;

import com.example.blog.model.User;
import com.example.blog.repository.UserRepository;
import com.example.blog.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(ObjectId id) {
        return userRepository.findById(id);
    }
}
