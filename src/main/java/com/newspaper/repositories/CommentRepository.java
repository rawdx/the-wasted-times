package com.newspaper.repositories;

import com.newspaper.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing {@link Comment} entities.
 * Uses Spring Data JPA for data access.
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
