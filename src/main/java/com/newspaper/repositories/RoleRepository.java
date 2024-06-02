package com.newspaper.repositories;

import com.newspaper.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for managing {@link Role} entities.
 * Uses Spring Data JPA for data access.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

    /**
     * Retrieves an optional {@link Role} entity based on the provided name.
     *
     * @param name The name to search for.
     * @return An optional {@link Role} entity.
     */
    Optional<Role> findByName(String name);
}
