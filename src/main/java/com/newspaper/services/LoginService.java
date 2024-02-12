package com.newspaper.services;

import com.newspaper.entities.dtos.UserDto;

/**
 * Service interface for user login operations.
 */
public interface LoginService {

	/**
	 * Attempts to log in a user with the provided email and password.
	 *
	 * @param email    The email of the user attempting to log in.
	 * @param password The password entered by the user.
	 * @return True if the login is successful, false otherwise.
	 */
	boolean loginUser(String email, String password);

	/**
	 * Retrieves user details for the given email.
	 *
	 * @param email The email of the user.
	 * @return The UserDto containing user details, or null if the user is not found.
	 */
	UserDto getUserDetails(String email);
}
