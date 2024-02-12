package com.newspaper.controllers;

import com.newspaper.services.VerificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Controller responsible for handling email verification-related requests.
 */
@Controller
public class VerificationController {

	private static final Logger logger = LoggerFactory.getLogger(VerificationController.class);

	private final VerificationService verificationService;

	public VerificationController(VerificationService verificationService) {
		this.verificationService = verificationService;
	}

	/**
	 * Handles the GET request for email verification using the provided token.
	 *
	 * @param token The verification token sent via email.
	 * @return The name of the view template to render after verification.
	 */
	@GetMapping("/verify/{token}")
	public String verifyEmail(@PathVariable String token) {
		try {
			boolean success = verificationService.verifyEmail(token);

			if (success) {
				logger.info("Email verification successful for token: {}", token);
            } else {
				logger.warn("Email verification failed for token: {}", token);
            }
            return "redirect:/profile";
        } catch (Exception e) {
			logger.error("Error during email verification for token: {}", token);
			return "redirect:/profile";
		}
	}

	/**
	 * Handles the GET request for resending the email verification using the provided email.
	 *
	 * @param email The user's email to resend the verification email.
	 * @return The name of the view template to render after attempting to resend the verification email.
	 */
	@GetMapping("/resend-verification")
	public String resendVerificationEmail(@RequestParam("email") String email) {
		try {
			boolean success = verificationService.resendVerificationEmail(email);

			if (success) {
				logger.info("Resent verification email successfully for email: {}", email);
            } else {
				logger.warn("Failed to resend verification email for email: {}", email);
            }
            return "redirect:/profile";
        } catch (Exception e) {
			logger.error("Error during resend verification email for email: {}", email);
			return "redirect:/profile";
		}
	}
}
