package com.codinghints.eventsmicroservice.dao;

import com.codinghints.eventsmicroservice.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {
    Optional<Event>  findByTitleContainingIgnoreCase(String title);
}
