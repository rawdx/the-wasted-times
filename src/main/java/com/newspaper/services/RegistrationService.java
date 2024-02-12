package com.newspaper.services;

import com.newspaper.entities.dtos.UserDto;

/**
 * Service interface for user registration operations.
 */
public interface RegistrationService {

    /**
     * Registers a new user based on the provided UserDto.
     *
     * @param user The UserDto containing user details for registration.
     * @return True if the registration is successful, false otherwise.
     */
	boolean registerUser(UserDto user);

    /**
     * Processes and combines the first name and last name into a full name.
     *
     * @param firstName The first name of the user.
     * @param lastName  The last name of the user.
     * @return The full name or null if both first name and last name are empty.
     */
    String processFullName(String firstName, String lastName);

    /**
     * Processes and validates the phone number.
     *
     * @param phoneNumber The phone number to be processed.
     * @return The processed phone number or null if it is empty.
     */
	String processPhoneNumber(String phoneNumber);
}
