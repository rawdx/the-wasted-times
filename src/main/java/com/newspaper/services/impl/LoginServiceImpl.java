package com.newspaper.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.newspaper.entities.User;
import com.newspaper.entities.dtos.UserDto;
import com.newspaper.repositories.UserRepository;
import com.newspaper.services.LoginService;
import com.newspaper.services.UserService;
import com.newspaper.utils.Encryptor;

/**
 * Implementation of the {@link LoginService} interface for user login
 * operations.
 */
@Service
public class LoginServiceImpl implements LoginService {

	private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

	private final UserRepository userRepository;
	private final UserService userService;

	public LoginServiceImpl(UserRepository userRepository, UserService userService) {
		this.userRepository = userRepository;
		this.userService = userService;
	}

	@Override
	public boolean loginUser(String email, String password) {
		Optional<User> userOptional = userRepository.findByEmail(email);

		if (userOptional.isPresent()) {
			User serverUser = userOptional.get();

			if (Encryptor.verifyPassword(password, serverUser.getCredential())) {
				return true;
			} else {
				logger.warn("Login failed - Invalid credentials for user: {}", email);
				return false;
			}
		} else {
			logger.warn("Login failed - User not found: {}", email);
			return false;
		}
	}

	@Override
	public UserDto getUserDetails(String email) {
		Optional<User> userOptional = userRepository.findByEmail(email);
		return userOptional.map(userService::convertToUserDto).orElse(null);
	}


}
