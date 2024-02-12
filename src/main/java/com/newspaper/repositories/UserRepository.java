package com.newspaper.repositories;

import com.newspaper.entities.User;
import com.newspaper.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for managing {@link User} entities.
 * Uses Spring Data JPA for data access.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Check if a user with the given email exists.
     *
     * @param email The email to check.
     * @return True if a user with the specified email exists; otherwise, false.
     */
    boolean existsByEmail(String email);

    /**
     * Retrieve a user by their email address.
     *
     * @param email The email address of the user.
     * @return An {@link Optional} containing the user if found, or empty if not found.
     */
    Optional<User> findByEmail(String email);

    /**
     * Count the number of users with a specific role.
     *
     * @param role The user role to count.
     * @return The count of users with the specified role.
     */
	long countByRole(UserRole role);
}
