package com.museumApplication.Backend.repo;

import com.museumApplication.Backend.entities.CommunityPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CommunityPostRepository extends JpaRepository<CommunityPost, UUID> {
}
