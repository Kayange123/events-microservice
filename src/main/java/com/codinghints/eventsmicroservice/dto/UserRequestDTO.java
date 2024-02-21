package com.codinghints.eventsmicroservice.dto;

import lombok.*;

@Builder @AllArgsConstructor @NoArgsConstructor
@Data @EqualsAndHashCode
public class UserRequestDTO {
    private String externalId;
    private String email;
    private String username;
    private String firstName;
    private String lastName;
    private String imageUrl;
}
