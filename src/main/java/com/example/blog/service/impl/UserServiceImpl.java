package com.example.blog.service.impl;

import com.example.blog.model.PasswordResetToken;
import com.example.blog.model.User;
import com.example.blog.repository.PasswordTokenRepository;
import com.example.blog.repository.UserRepository;
import com.example.blog.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PasswordTokenRepository passwordTokenRepository;

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(ObjectId id) {
        return userRepository.findById(id);
    }

    @Override
    public User signUpUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User userSave = userRepository.save(user);
        return userSave;
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void createPasswordResetTokenForUser(Optional<User> user, String token) {
        PasswordResetToken myToken = new PasswordResetToken(token, user.get().get_id());
        passwordTokenRepository.save(myToken);
    }
}
