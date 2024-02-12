package com.newspaper.services;

import com.newspaper.entities.Token;
import com.newspaper.entities.User;

/**
 * Service interface for token-related operations.
 */
public interface TokenService {
	
    /**
     * Checks if a token is expired based on its expiration date.
     *
     * @param token The token to be checked for expiration.
     * @return {@code true} if the token is expired; {@code false} otherwise.
     */
    boolean isExpired(Token token);
    
    /**
     * Generates a unique token.
     *
     * @return The generated token as a String.
     */
	String generateToken();
	
    /**
     * Saves a token for the specified user.
     *
     * @param user The user for whom the token is generated.
     * @param token The value of the token to be saved.
     */
    void saveToken(User user, String token);
}
