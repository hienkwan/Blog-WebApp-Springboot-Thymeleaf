package com.example.blog.service;

import com.example.blog.model.PasswordResetToken;

import java.util.Optional;

public interface PasswordResetTokenService {
    Optional<PasswordResetToken> findPasswordResetTokenByToken(String token);
}
