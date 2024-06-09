package com.newspaper.controllers;

import com.newspaper.services.VerificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


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
	public String verifyEmail(RedirectAttributes redirectAttributes, @PathVariable String token) {
		try {
			boolean success = verificationService.verifyEmail(token);

			if (success) {
				logger.info("Email verification successful for token: {}", token);
				redirectAttributes.addFlashAttribute("errorMessage", "Email verification successful.");
            } else {
				redirectAttributes.addFlashAttribute("errorMessage", "Email verification failed.");
				logger.warn("Email verification failed for token: {}", token);
            }
            return "redirect:/";
        } catch (Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage", "An error occurred during email verification.");
			logger.error("Error during email verification for token: {}", token);
			return "redirect:/";
		}
	}

	/**
	 * Handles the GET request for resending the email verification using the provided email.
	 *
	 * @param email The user's email to resend the verification email.
	 * @return The name of the view template to render after attempting to resend the verification email.
	 */
	@GetMapping("/resend-verification")
	public String resendVerificationEmail(RedirectAttributes redirectAttributes, @RequestParam("email") String email) {
		try {
			boolean success = verificationService.resendVerificationEmail(email);

			if (success) {
				redirectAttributes.addFlashAttribute("successMessage", "Verification email sent successfully.");
				logger.info("Resent verification email successfully for email: {}", email);
            } else {
				redirectAttributes.addFlashAttribute("errorMessage", "Failed to send verification email.");
				logger.warn("Failed to resend verification email for email: {}", email);
            }
            return "redirect:/profile";
        } catch (Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage", "An error occurred while sending verification email.");
			logger.error("Error during resend verification email for email: {}", email);
			return "redirect:/profile";
		}
	}
}
