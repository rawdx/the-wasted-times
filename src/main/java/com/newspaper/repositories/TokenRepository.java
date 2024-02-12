package com.newspaper.repositories;

import com.newspaper.entities.Token;
import com.newspaper.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for managing {@link Token} entities.
 * Uses Spring Data JPA for data access.
 */
@Repository
public interface TokenRepository extends JpaRepository<Token, Long>  {
	
    /**
     * Retrieves an optional {@link Token} entity based on the provided token value.
     *
     * @param token The token value to search for.
     * @return An optional {@link Token} entity.
     */
	Optional<Token> findByToken(String token);
	
    /**
     * Retrieves an optional {@link Token} entity associated with the provided {@link User}.
     *
     * @param user The user to search for.
     * @return An optional {@link Token} entity.
     */
	Optional<Token> findByUser(User user);
}
