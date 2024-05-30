package com.newspaper.services;

import com.newspaper.entities.User;
import com.newspaper.entities.dtos.RoleDto;
import com.newspaper.entities.dtos.UserDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Service interface for user-related operations.
 */
public interface UserService {

	/**
	 * Converts a {@link UserDto} object to a {@link User} entity.
	 *
	 * @param userDto The {@link UserDto} to convert.
	 * @return The corresponding {@link User} entity.
	 */
	User convertToUserEntity(UserDto userDto);

	/**
	 * Converts a {@link User} entity to a {@link UserDto}.
	 *
	 * @param user The {@link User} entity to convert.
	 * @return The corresponding {@link UserDto}.
	 */
	UserDto convertToUserDto(User user);

	/**
	 * Creates a new user based on the provided {@link UserDto}.
	 *
	 * @param userDto The {@link UserDto} containing user information.
	 * @return The created {@link User} entity.
	 */
	User createUser(UserDto userDto);

	/**
	 * Updates the verification status and role of a user based on the email address.
	 *
	 * @param email      The email address of the user to update.
	 * @param isVerified The verification status to set.
	 * @param role       The role to set.
	 * @return True if the user is successfully updated, false otherwise.
	 */

	boolean updateUser(String email, boolean isVerified, RoleDto role);

	/**
	 * Deletes a user based on the email address. Handles the deletion logic and checks for the last remaining admin.
	 *
	 * @param email The email address of the user to delete.
	 * @return True if the user is successfully deleted, false otherwise.
	 */
	boolean deleteUser(String email);

	/**
	 * Validates the email address using a regular expression.
	 *
	 * @param email The email address to be validated.
	 * @return True if the email is valid, false otherwise.
	 */
	boolean isValidEmail(String email);

	/**
	 * Update the user's email address.
	 *
	 * @param email    The current email address.
	 * @param newEmail The new email address.
	 * @return True if the update is successful, false otherwise.
	 */
	boolean updateEmail(String email, String newEmail);

	/**
	 * Update the user's name.
	 *
	 * @param email The email address of the user.
	 * @param name  The new name.
	 * @return True if the update is successful, false otherwise.
	 */
	boolean updateName(String email, String name);

	/**
	 * Update the user's phone number.
	 *
	 * @param email The email address of the user.
	 * @param phone The new phone number.
	 * @return True if the update is successful, false otherwise.
	 */
	boolean updatePhone(String email, String phone);

	/**
	 * Update the user's profile picture.
	 *
	 * @param email The email address of the user.
	 * @param file  The new profile picture file.
	 * @return True if the update is successful, false otherwise.
	 * @throws IOException If there is an error reading the file.
	 */
	boolean updateProfilePicture(String email, MultipartFile file) throws IOException;
}
