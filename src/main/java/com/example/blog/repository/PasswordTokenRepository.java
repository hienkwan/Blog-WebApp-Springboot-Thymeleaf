package com.example.blog.repository;

import com.example.blog.model.PasswordResetToken;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PasswordTokenRepository extends MongoRepository<PasswordResetToken,String> {
    Optional<PasswordResetToken> findPasswordResetTokenByToken(String token);
}
