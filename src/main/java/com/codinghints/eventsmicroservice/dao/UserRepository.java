package com.codinghints.eventsmicroservice.dao;

import com.codinghints.eventsmicroservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
