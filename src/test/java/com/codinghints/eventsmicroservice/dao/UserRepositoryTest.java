package com.codinghints.eventsmicroservice.dao;

import com.codinghints.eventsmicroservice.model.Event;
import com.codinghints.eventsmicroservice.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void printAllUsers(){
        List<User> users = userRepository.findAll();

        System.out.println("users = " + users);
    }

    @Test
    public void saveUser(){
        Event event = Event.builder()
                .isFree(true)
                .title("My first event")
                .description("My first event")
                .url("https://google.com")
                .build();

        User user = User.builder()
                .email("user@example.com")
                .username("JohnCena")
                .firstName("John")
                .lastName("Cena")
                .events(List.of(event))
                .build();

        userRepository.save(user);
    }

}