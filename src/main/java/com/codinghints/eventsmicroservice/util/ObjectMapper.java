package com.codinghints.eventsmicroservice.util;

import com.codinghints.eventsmicroservice.dto.UserRequestDTO;
import com.codinghints.eventsmicroservice.dto.UserResponseDTO;
import com.codinghints.eventsmicroservice.model.User;

public class ObjectMapper {
    public static User convertToUserEntity(UserRequestDTO user){
        return User.builder()
                .email(user.getEmail())
                .externalId(user.getExternalId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .imageUrl(user.getImageUrl())
                .username(user.getUsername())
                .build();
    }

    public static UserResponseDTO convertToUserResponseDTO(User user){
        return UserResponseDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .externalId(user.getExternalId())
                .imageUrl(user.getImageUrl())
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }
}
