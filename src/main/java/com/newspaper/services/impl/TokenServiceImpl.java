package com.newspaper.services.impl;

import com.newspaper.entities.Token;
import com.newspaper.entities.User;
import com.newspaper.repositories.TokenRepository;
import com.newspaper.services.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Implementation of the {@link TokenService} interface for token-related operations.
 */
@Service
public class TokenServiceImpl implements TokenService {

	private static final int TOKEN_EXPIRATION_DAYS = 1;

	private static final Logger logger = LoggerFactory.getLogger(TokenServiceImpl.class);
	
	private final TokenRepository tokenRepository;
	
	public TokenServiceImpl(TokenRepository tokenRepository) {
		this.tokenRepository = tokenRepository;
	}

	@Override
	public String generateToken() {
		return UUID.randomUUID().toString();
	}

	@Override
	public void saveToken(User user, String tokenValue) {
		try {
			Token token = new Token();

			token.setToken(tokenValue);
			token.setUser(user);
			token.setExpiryDate(LocalDateTime.now().plusDays(TOKEN_EXPIRATION_DAYS));

			tokenRepository.save(token);
			logger.info("Token saved for user: {}", user.getEmail());
		} catch (Exception e) {
			logger.error("Error saving token for user: {}", user.getEmail());
		}
	}

	@Override
	public boolean isExpired(Token token) {
		return LocalDateTime.now().isAfter(token.getExpiryDate());
	}
}
