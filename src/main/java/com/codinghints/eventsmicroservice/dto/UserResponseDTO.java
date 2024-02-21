package com.codinghints.eventsmicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder @NoArgsConstructor @AllArgsConstructor
public class UserResponseDTO {
    private UUID id;
    private String externalId;
    private String email;
    private String username;
    private String firstName;
    private String lastName;
    private String imageUrl;
}
