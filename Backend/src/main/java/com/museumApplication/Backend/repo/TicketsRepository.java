package com.museumApplication.Backend.repo;

import com.museumApplication.Backend.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TicketsRepository extends JpaRepository<Ticket, UUID> {
}
