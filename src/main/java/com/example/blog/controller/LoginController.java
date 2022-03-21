package com.example.blog.controller;

import com.example.blog.model.PasswordResetModelView;
import com.example.blog.model.PasswordResetToken;
import com.example.blog.model.User;
import com.example.blog.service.PasswordResetTokenService;
import com.example.blog.service.UserService;
import com.example.blog.util.EmailSender;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @Autowired
    private EmailSender emailSender;

    @Autowired
    private PasswordResetTokenService passwordResetTokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping(value = "/login")
    public String login() {
        System.out.println("aaaaaaaaaaaaaaaa");
        return "/home";
    }

    @GetMapping(value = "/login")
    public String loginPage() {
        return "/login";
    }

    @GetMapping(value = "/logout")
    public String logout() {
        return "/login";
    }

    @PostMapping(value = "/signup", consumes = {"application/json"})
    public String signUp(@RequestBody User user) {
        userService.signUpUser(user);
        return "redirect:login";
    }

    @PostMapping(value = "/resetPassword")
    public ResponseEntity resetPassword(@RequestParam String email) {
        Optional<User> user = userService.findUserByEmail(email);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException("username not found");
        } else {
            System.out.println("find user....................");
            String token = UUID.randomUUID().toString();
            userService.createPasswordResetTokenForUser(user, token);
            emailSender.sendMail(email, "Change password link", "http://localhost:8080/newPassword?token=" + token);
            return ResponseEntity.status(HttpStatus.OK).body("Email sent");
        }
    }

    @GetMapping(value = "/newPassword")
    public String resetNewPassword(@RequestParam String token, Model model) {
        //implement verify token logic
        PasswordResetToken passwordResetToken = passwordResetTokenService.findPasswordResetTokenByToken(token).get();
        String tokenGetInDatabase = passwordResetToken.getToken();
        String userId = passwordResetToken.getUserId().toString();
        if (!tokenGetInDatabase.isEmpty()) {
            //implement token is expire logic
            model.addAttribute("userId", userId);
            return "/forgotPassword";
        } else {
            return "error";
        }
    }

    @PostMapping(value = "/updatePassword")
    public String updatePassword(@RequestBody PasswordResetModelView passwordResetModelView) {
        ObjectId userIdToObjectId = new ObjectId(passwordResetModelView.getUserId().toString());
        Optional<User> user = userService.getUserById(userIdToObjectId);

        if (user.isPresent()) {
            Optional<User> userNeedUpdate = userService.getUserById(userIdToObjectId);
            if (userNeedUpdate.isPresent()) {
                userNeedUpdate.get().setPassword(passwordEncoder.encode(passwordResetModelView.getPassword()));
                userService.updateUser(userNeedUpdate.get());
                return "redirect:login";
            } else {
                return "error";
            }
        }
        return "login";
    }
}
