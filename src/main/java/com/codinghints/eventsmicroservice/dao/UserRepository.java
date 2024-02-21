package com.codinghints.eventsmicroservice.dao;

import com.codinghints.eventsmicroservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsernameContainingIgnoreCase(String john);

    Optional<User> findByEmail(String email);
}
