package com.codinghints.eventsmicroservice.dao;

import com.codinghints.eventsmicroservice.entity.Confirmation;
import com.codinghints.eventsmicroservice.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConfirmationRepository extends JpaRepository<Confirmation, Long> {
    Optional<Confirmation> findByKey(String key);
    Optional<Confirmation> findByUserEntity(UserEntity userEntity);
}
