package com.newspaper.services;

/**
 * Service interface for handling email-related operations.
 */
public interface EmailService {

    /**
     * Sends a verification email to the specified email address with the provided token.
     *
     * @param email The recipient's email address.
     * @param token The verification token.
     * @return True if the email is successfully sent, false otherwise.
     */
	boolean sendVerificationEmail(String email, String token);

    /**
     * Sends a password change email to the specified email address with the provided token.
     *
     * @param email The recipient's email address.
     * @param token The password change token.
     * @return True if the email is successfully sent, false otherwise.
     */
	boolean sendPasswordChangeEmail(String email, String token);
}
