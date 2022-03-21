package com.example.blog.service.impl;

import com.example.blog.model.PasswordResetToken;
import com.example.blog.repository.PasswordTokenRepository;
import com.example.blog.service.PasswordResetTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PasswordResetTokenServiceImpl implements PasswordResetTokenService {
    @Autowired
    private PasswordTokenRepository passwordTokenRepository;

    @Override
    public Optional<PasswordResetToken> findPasswordResetTokenByToken(String token) {
        return passwordTokenRepository.findPasswordResetTokenByToken(token);
    }
}
