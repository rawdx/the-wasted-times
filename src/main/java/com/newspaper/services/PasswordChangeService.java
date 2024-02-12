package com.newspaper.services;

/**
 * Service interface for password change operations.
 */
public interface PasswordChangeService {


    /**
     * Initiates the password change process for the user with the given email.
     *
     * @param email The email of the user.
     * @return True if the initiation is successful, false otherwise.
     */
    boolean initiatePasswordChange(String email);

    /**
     * Changes the password for the user using the provided token.
     *
     * @param token      The password change token.
     * @param newPassword The new password.
     * @return True if the password change is successful, false otherwise.
     */
    boolean changePassword(String token, String newPassword);
}
