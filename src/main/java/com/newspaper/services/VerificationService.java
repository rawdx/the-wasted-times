package com.newspaper.services;

import com.newspaper.entities.User;

/**
 * Service interface for email verification operations.
 */
public interface VerificationService {

    /**
     * Initiates email verification for the given user.
     *
     * @param user The user for whom email verification is initiated.
     * @return True if the initiation is successful, false otherwise.
     */
    boolean initiateEmailVerification(User user);

    /**
     * Verifies email using the provided token.
     *
     * @param token The verification token.
     * @return True if the verification is successful, false otherwise.
     */
    boolean verifyEmail(String token);

    /**
     * Resends the verification email for the user with the given email.
     *
     * @param email The email of the user.
     * @return True if the email is resent successfully, false otherwise.
     */
    boolean resendVerificationEmail(String email);
}
