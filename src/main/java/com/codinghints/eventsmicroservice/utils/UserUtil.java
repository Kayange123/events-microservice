package com.codinghints.eventsmicroservice.utils;

import com.codinghints.eventsmicroservice.entity.RoleEntity;
import com.codinghints.eventsmicroservice.entity.UserEntity;

import java.time.LocalDateTime;
import java.util.UUID;

public class UserUtil {
    private static final String defaultImage = "https://cdn-icons-png.flaticon.com/512/149/149071.png";
    public static UserEntity createUserEntity(String firstName, String lastName, String  email, RoleEntity role){
        return UserEntity.builder()
                .userId(UUID.randomUUID().toString())
                .firstName(firstName)
                .lastName(lastName)
                .lastLogin(LocalDateTime.now())
                .email(email)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .enabled(false)
                .loginAttempts(0)
                .qrCodeSecret("")
                .phone("")
                .bio("")
                .imageUrl(defaultImage)
                .role(role)
                .build();
    }
}
