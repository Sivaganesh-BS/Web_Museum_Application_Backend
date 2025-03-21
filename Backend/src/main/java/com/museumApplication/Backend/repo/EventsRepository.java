package com.museumApplication.Backend.repo;

import com.museumApplication.Backend.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EventsRepository extends JpaRepository<Event, UUID> {
}
