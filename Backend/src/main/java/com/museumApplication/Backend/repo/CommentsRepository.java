package com.museumApplication.Backend.repo;

import com.museumApplication.Backend.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface CommentsRepository extends JpaRepository<Comment, UUID> {

}
