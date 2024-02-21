package com.codinghints.eventsmicroservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Setter @AllArgsConstructor @NoArgsConstructor
@Getter @Builder @Entity @Table(name = "users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(updatable = false, unique = true)
    private String externalId;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String username;
    private String firstName;
    private String lastName;
    private String imageUrl;

    @OneToMany(mappedBy = "host", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Event> events;

    @CreatedDate
    private Date createdAt;
    @LastModifiedDate
    private Date updatedAt;
}
