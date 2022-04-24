package com.example.demo.repository;

import com.example.demo.DemoApplicationTests;
import framework.com.example.demo.model.entity.User;
import framework.com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class UserRepositoryTests extends DemoApplicationTests {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void create(){

        User user = new User();
        user.setAccount("pizza301");
        user.setPassword("password");
        user.setPhoneNumber("01000000000");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("admin");
        user.setEmail("test@test.com");
        user.setUpdatedAt(LocalDateTime.now());
        User newUser = userRepository.save(user);
        System.out.println(newUser);
    }
}
