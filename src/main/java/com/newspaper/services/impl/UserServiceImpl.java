package com.newspaper.services.impl;

import com.newspaper.entities.Role;
import com.newspaper.entities.Token;
import com.newspaper.entities.User;
import com.newspaper.entities.dtos.RoleDto;
import com.newspaper.entities.dtos.UserDto;
import com.newspaper.repositories.TokenRepository;
import com.newspaper.repositories.UserRepository;
import com.newspaper.services.RoleService;
import com.newspaper.services.UserService;
import com.newspaper.services.VerificationService;
import com.newspaper.utils.ImageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

/**
 * Implementation of the {@link UserService} interface for user-related
 * operations.
 */
@Service
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	private final UserRepository userRepository;
	private final TokenRepository tokenRepository;
	private final VerificationService verificationService;
	private final RoleService roleService;

	public UserServiceImpl(UserRepository userRepository, TokenRepository tokenRepository, VerificationService verificationService, RoleService roleService) {
		this.userRepository = userRepository;
		this.tokenRepository = tokenRepository;
        this.verificationService = verificationService;
        this.roleService = roleService;
    }

	@Override
	public User convertToUserEntity(UserDto userDto) {
		User user = new User();

		user.setEmail(userDto.getEmail());
		user.setCredential(userDto.getCredential());
		user.setName(userDto.getName());
		user.setPhoneNumber(userDto.getPhoneNumber());
		user.setProfilePicture(ImageUtils.convertToByteArray(userDto.getProfilePicture()));
		user.setVerified(userDto.isVerified());
		user.setRole(roleService.convertToRoleEntity(userDto.getRole()));

		return user;
	}

	@Override
	public UserDto convertToUserDto(User user) {
		UserDto userDto = new UserDto();

		userDto.setId(user.getId());
		userDto.setEmail(user.getEmail());
		userDto.setName(user.getName());
		userDto.setPhoneNumber(user.getPhoneNumber());
		userDto.setProfilePicture(ImageUtils.convertToBase64(user.getProfilePicture()));
		userDto.setVerified(user.isVerified());
		userDto.setRole(roleService.convertToRoleDto(user.getRole()));

		return userDto;
	}

	@Override
	public User createUser(UserDto userDto) {
		return userRepository.save(convertToUserEntity(userDto));
	}

	@Override
	public boolean updateUser(String email, boolean isVerified, RoleDto roleDto) {
		Optional<User> optionalUser = userRepository.findByEmail(email);
		
		if (optionalUser.isPresent()) {
			User user = optionalUser.get();

			Role role = roleService.convertToRoleEntity(roleDto);
			user.setVerified(isVerified);
			user.setRole(role);
			
			userRepository.save(user);
			logger.info("User details updated with email: {}", email);
			return true;
		} else {
			logger.warn("Attempted to update non-existing user with email: {}", email);
			return false;
		}
	}
	
	@Override
	public boolean deleteUser(String email) {
		Optional<User> optionalUser = userRepository.findByEmail(email);

		if (optionalUser.isPresent()) {
			User user = optionalUser.get();
			RoleDto role = roleService.getRoleByName("ADMIN");

			// Check if the user being deleted is an admin
			boolean isUserAdmin = user.getRole().getId() == role.getId();

			// Check the count of remaining admin users
			long remainingAdminCount = userRepository.countByRole(roleService.convertToRoleEntity(role));

			if (isUserAdmin && remainingAdminCount == 1) {
				logger.warn("Attempted to delete the last remaining admin: {}. User deletion aborted.", email);
				return false;
			} else {
				Optional<Token> optionalToken = tokenRepository.findByUser(user);

				optionalToken.ifPresent(token -> {
					tokenRepository.delete(token);
					logger.info("Token deleted for user: {}", email);
				});
				userRepository.delete(user);
				logger.info("User deleted with email: {}", email);
				return true;
			}
		} else {
			logger.warn("Attempted to delete non-existing user with email: {}", email);
			return false;
		}
	}

	@Override
	public boolean isValidEmail(String email) {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
		return email.matches(emailRegex);
	}

	@Override
	public boolean updateEmail(String email, String newEmail) {
		if (!isValidEmail(newEmail)) {
			return false;
		}

		if (userRepository.existsByEmail(newEmail)) {
			return false;
		}

		Optional<User> optionalUser = userRepository.findByEmail(email);

		if (optionalUser.isPresent()) {
			User user = optionalUser.get();

			user.setEmail(newEmail);
			user.setVerified(false);

			userRepository.save(user);

			verificationService.resendVerificationEmail(newEmail);
			return true;
		}
		return false;
	}

	@Override
	public boolean updateName(String email, String name) {
		Optional<User> optionalUser = userRepository.findByEmail(email);

		if (optionalUser.isPresent()) {
			User user = optionalUser.get();

			user.setName(name);

			userRepository.save(user);
			return true;
		}
		return false;
	}

	@Override
	public boolean updatePhone(String email, String phone) {
		Optional<User> optionalUser = userRepository.findByEmail(email);

		if (optionalUser.isPresent()) {
			User user = optionalUser.get();

			user.setPhoneNumber(phone);

			userRepository.save(user);
			return true;
		}
		return false;
	}

	@Override
	public boolean updateProfilePicture(String email, MultipartFile file) throws IOException {
		if (file != null && !file.isEmpty()) {
			Optional<User> optionalUser = userRepository.findByEmail(email);

			if (optionalUser.isPresent()) {
				User user = optionalUser.get();

				user.setProfilePicture(file.getBytes());

				userRepository.save(user);
				return true;
			}
		}
		return false;
	}
}