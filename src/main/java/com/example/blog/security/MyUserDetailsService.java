package com.example.blog.security;

import com.example.blog.model.User;
import com.example.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Optional;

import static org.springframework.security.core.userdetails.User.withUsername;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByEmail(username);
        System.out.println("In loadUserByUsername method");
        if (!user.isPresent()) {
            throw new UsernameNotFoundException("User name not found");
        }
        System.out.println(user.toString());
        PasswordEncoder b = new BCryptPasswordEncoder();
        System.out.println(b.encode("123456"));
        MyUserPrincipal userPrincipal = new MyUserPrincipal();
        userPrincipal.setUser(user.get());
//        return withUsername(user.getEmail())
//                .password(user.getPassword())
//                .accountExpired(false)
//                .accountLocked(false)
//                .accountExpired(false)
//                .authorities(new SimpleGrantedAuthority("ROLE_ADMIN"))
//                .build();
        return userPrincipal;
    }
}
