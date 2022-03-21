package com.example.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "passwordResetToken")
public class PasswordResetToken {
    private static final int EXPIRATION = 60 * 24;

    @MongoId
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    private String token;

    private ObjectId userId;

    private Date expiryDate;

    public PasswordResetToken(String token, ObjectId userId) {
        this.token = token;
        this.userId = userId;
    }
}
