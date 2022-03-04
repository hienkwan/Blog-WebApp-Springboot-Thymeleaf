package com.example.blog.security;

public enum Role {
    USER(0),ADMIN(1);

    private int id;

    Role(int id) {
        this.id=id;
    }
}
