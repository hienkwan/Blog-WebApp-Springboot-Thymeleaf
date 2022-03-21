package com.example.blog.controller;

import com.example.blog.util.EmailSender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LoginControllerTest {

    @Autowired
    private EmailSender emailSender;

    @Test
    public void testEmailSend(){
        emailSender.sendMail("hienkwan@gmail.com",
                "Reset password link",
                "localhost:8080/egreger");

    }
}