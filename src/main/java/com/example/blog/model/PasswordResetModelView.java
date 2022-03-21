package com.example.blog.model;

import lombok.Data;
import org.bson.types.ObjectId;

@Data
public class PasswordResetModelView {
    private ObjectId UserId;
    private String password;
}
