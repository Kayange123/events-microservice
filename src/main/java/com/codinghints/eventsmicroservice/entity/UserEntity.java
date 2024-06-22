package com.codinghints.eventsmicroservice.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Setter @Getter @Builder @ToString
@NoArgsConstructor @AllArgsConstructor
@Entity @Table(name = "users")
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class UserEntity extends Auditable{
    @Column(unique = true, updatable = false, nullable = false)
    private String userId;
    private String firstName;
    private String lastName;
    @Column(unique = true, updatable = false, nullable = false)
    private String email;
    private Integer loginAttempts;
    private LocalDateTime lastLogin;
    private String phone;
    private String bio;
    private String imageUrl;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean locked;
    private boolean mfa;
    private boolean enabled;
    @JsonIgnore
    private String qrCodeSecret;
    @Column(columnDefinition = "text")
    private String qrCodeImageUri;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private RoleEntity role;
}
