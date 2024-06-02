package com.newspaper.services.impl;

import com.newspaper.entities.User;
import com.newspaper.entities.dtos.UserDto;
import com.newspaper.repositories.UserRepository;
import com.newspaper.services.RegistrationService;
import com.newspaper.services.UserService;
import com.newspaper.services.VerificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Implementation of the {@link RegistrationService} interface for user
 * registration operations.
 */
@Service
public class RegistrationServiceImpl implements RegistrationService {

	private static final Logger logger = LoggerFactory.getLogger(RegistrationServiceImpl.class);

	private final UserRepository userRepository;
	private final UserService userService;
	private final VerificationService verificationService;

	public RegistrationServiceImpl(UserRepository userRepository, UserService userService,
			VerificationService verificationService) {
		this.userRepository = userRepository;
		this.userService = userService;
		this.verificationService = verificationService;
	}

	@Override
	public boolean registerUser(UserDto user) {
        if (!userService.isValidEmail(user.getEmail())) {
            logger.warn("Sign up failed - Invalid email address: {}", user.getEmail());
            return false;
        }

		if (userRepository.existsByEmail(user.getEmail())) {
			logger.warn("Registration failed - Email already exists: {}", user.getEmail());
			return false;
		}
		try {
			User createdUser = userService.createUser(user);
			logger.info("User created successfully: {}", user.getEmail());

			verificationService.initiateEmailVerification(createdUser);
			return true;
		} catch (Exception e) {
			logger.error("Registration failed - Error when creating user: {}", e.getMessage());
			return false;
		}
	}

	@Override
	public String processFullName(String firstName, String lastName) {
		firstName = (firstName == null) ? "" : firstName.trim().replaceAll("\\s+", " ");
		lastName = (lastName == null) ? "" : lastName.trim().replaceAll("\\s+", " ");


		if (firstName.isEmpty() && lastName.isEmpty()) {
			return null;
		} else if (firstName.isEmpty()) {
			return lastName;
		} else if (lastName.isEmpty()) {
			return firstName;
		} else {
			return firstName + " " + lastName;
		}
	}

	@Override
	public String processPhoneNumber(String phoneNumber) {
		return phoneNumber.isEmpty() ? null : phoneNumber;
	}
}
