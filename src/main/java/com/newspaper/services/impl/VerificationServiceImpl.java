package com.newspaper.services.impl;

import com.newspaper.entities.Token;
import com.newspaper.entities.User;
import com.newspaper.repositories.TokenRepository;
import com.newspaper.repositories.UserRepository;
import com.newspaper.services.EmailService;
import com.newspaper.services.TokenService;
import com.newspaper.services.VerificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implementation of the {@link VerificationService} interface for email verification operations.
 */
@Service
public class VerificationServiceImpl implements VerificationService {

	private static final Logger logger = LoggerFactory.getLogger(VerificationServiceImpl.class);
	
	private final UserRepository userRepository;
	private final TokenRepository tokenRepository;
	private final TokenService tokenService;
	private final EmailService emailService;

	public VerificationServiceImpl(UserRepository userRepository, TokenRepository tokenRepository, TokenService tokenService, EmailService emailService) {
		this.userRepository = userRepository;
		this.tokenRepository = tokenRepository;
		this.tokenService = tokenService;
		this.emailService = emailService;
	}

	@Override
	public boolean initiateEmailVerification(User user) {
		try {
			String verificationToken = tokenService.generateToken();
			tokenService.saveToken(user, verificationToken);

			if(emailService.sendVerificationEmail(user.getEmail(), verificationToken)) {
				logger.info("Email verification initiated successfully for user: {}", user.getEmail());
				return true;
			} else {
				logger.warn("Failed to send email verification for user: {}", user.getEmail());
				return false;
			}
		} catch (Exception e) {
			logger.error("Error initiating email verification for user: {}", user.getEmail(), e);
			return false;
		}
	}

	@Override
	public boolean verifyEmail(String token) {
		try {
			Optional<Token> tokenOptional = tokenRepository.findByToken(token);

			if (tokenOptional.isPresent()) {
				Token verificationToken = tokenOptional.get();

				if (!tokenService.isExpired(verificationToken)) {
					User user = verificationToken.getUser();
					user.setVerified(true);
					userRepository.save(user);
					tokenRepository.delete(verificationToken);
					logger.info("Email verification successful for user: {}", user.getEmail());
					return true;
				}
			}
			logger.warn("Email verification failed for token: {}", token);
			return false;
		} catch (Exception e) {
			logger.error("Error verifying email for token: {}", token, e);
			return false;
		}
	}

	@Override
	public boolean resendVerificationEmail(String email) {
		try {
			Optional<User> userOptional = userRepository.findByEmail(email);

			if (userOptional.isPresent()) {
				User user = userOptional.get();

				if (!user.isVerified()) {
					Optional<Token> existingTokenOptional = tokenRepository.findByUser(user);

					if (existingTokenOptional.isPresent()) {
						Token existingToken = existingTokenOptional.get();

						if (!tokenService.isExpired(existingToken)) {
							tokenRepository.delete(existingToken);
							return initiateEmailVerification(user);
						} else {
							return emailService.sendVerificationEmail(email, existingToken.getToken());
						}
					} else {
						return initiateEmailVerification(user);
					}
				} else {
					logger.warn("User already verified with email: {}", email);
					return false;
				}
			} else {
				logger.warn("User not found with email: {}", email);
				return false;
			}
		} catch (Exception e) {
			logger.error("Error resending verification email to: {}", email, e);
			return false;
		}
	}
}
