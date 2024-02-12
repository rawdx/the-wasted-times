package com.newspaper.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.newspaper.entities.Token;
import com.newspaper.entities.User;
import com.newspaper.repositories.TokenRepository;
import com.newspaper.repositories.UserRepository;
import com.newspaper.services.EmailService;
import com.newspaper.services.PasswordChangeService;
import com.newspaper.services.TokenService;

/**
 * Implementation of the {@link PasswordChangeService} interface for password change operations.
 */
@Service
public class PasswordChangeServiceImpl implements PasswordChangeService {

	private static final Logger logger = LoggerFactory.getLogger(PasswordChangeServiceImpl.class);
	
	private final UserRepository userRepository;
	private final TokenRepository tokenRepository;
	private final TokenService tokenService;
	private final EmailService emailService;

	public PasswordChangeServiceImpl(UserRepository userRepository, TokenRepository tokenRepository, TokenService tokenService, EmailService emailService) {
		this.userRepository = userRepository;
		this.tokenRepository = tokenRepository;
		this.tokenService = tokenService;
		this.emailService = emailService;
	}

	@Override
	public boolean initiatePasswordChange(String email) {
		try {
			Optional<User> userOptional = userRepository.findByEmail(email);

			if (userOptional.isPresent()) {
				User user = userOptional.get();

				Optional<Token> tokenOptional = tokenRepository.findByUser(user);

				if (tokenOptional.isPresent()) {
					Token token = tokenOptional.get();

					if (tokenService.isExpired(token)) {
						tokenRepository.delete(token);
						logger.info("Deleted existing token for user: {}", email);
						
						String passwordChangeToken = tokenService.generateToken();
						tokenService.saveToken(user, passwordChangeToken);
						
						return emailService.sendPasswordChangeEmail(user.getEmail(), passwordChangeToken);
					} else {
						return emailService.sendPasswordChangeEmail(user.getEmail(), token.getToken());
					}
				} else {
					String passwordChangeToken = tokenService.generateToken();
					tokenService.saveToken(user, passwordChangeToken);
					
					return emailService.sendPasswordChangeEmail(user.getEmail(), passwordChangeToken);
				}
			} else {
				logger.warn("User not found with email: {}", email);
				return false;
			}
		} catch (Exception e) {
			logger.error("Error initiating password change for user: {}", email, e);
			return false;
		}
	}

	@Override
	public boolean changePassword(String token, String newPassword) {
		try {
			Optional<Token> tokenOptional = tokenRepository.findByToken(token);

			if (tokenOptional.isPresent()) {
				Token passwordChangeToken = tokenOptional.get();

				if (!tokenService.isExpired(passwordChangeToken)) {
					User user = passwordChangeToken.getUser();
					user.setCredential(newPassword);
					userRepository.save(user);
					tokenRepository.delete(passwordChangeToken);
					logger.info("Password change successful for user: {}", user.getEmail());
					return true;
				}
			}
			logger.warn("Token not found or expired: {}", token);
			return false;
		} catch (Exception e) {
			logger.error("Error changing password for token: {}", token, e);
			return false;
		}
	}
}
