package com.codinghints.eventsmicroservice.dao;

import com.codinghints.eventsmicroservice.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmailIgnoreCase(String email);
    Optional<UserEntity> findUserByUserId(String userId);
 }
