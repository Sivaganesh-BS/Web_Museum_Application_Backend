package com.museumApplication.Backend.repo;

import com.museumApplication.Backend.entities.Payments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PaymentsRepository extends JpaRepository<Payments, UUID> {
}
