package com.newspaper.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.newspaper.services.PasswordChangeService;
import com.newspaper.utils.Encryptor;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller responsible for handling password change-related requests.
 */
@Controller
public class PasswordChangeController {

	private static final Logger logger = LoggerFactory.getLogger(PasswordChangeController.class);

	private final PasswordChangeService passwordChangeService;

	public PasswordChangeController(PasswordChangeService passwordChangeService) {
		this.passwordChangeService = passwordChangeService;
	}

	/**
	 * Initiates the password change process for the specified email address.
	 *
	 * @param email The email address of the user.
	 * @return A redirect to the profile page ("/profile").
	 */
	@GetMapping("/initiate-password-change")
	public String initiatePasswordChange(RedirectAttributes redirectAttributes, @RequestParam String email) {
		try {
			if (passwordChangeService.initiatePasswordChange(email)) {
				redirectAttributes.addFlashAttribute("successMessage", "Password change email sent successfully.");
				logger.info("Password change initiated for user: {}", email);
            } else {
				redirectAttributes.addFlashAttribute("errorMessage", "Failed to send the password change email.");
				logger.warn("Password change failed for user: {}", email);
            }
            return "redirect:/profile";
        } catch (Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage", "An error occurred during the password change.");
			logger.error("Error initiating password change for email: {}", email, e);
			return "redirect:/profile";
		}
	}

	/**
	 * Handles the forgot password functionality by initiating a password change process for the given email.
	 *
	 * @param redirectAttributes the attributes for a redirect scenario
	 * @param email the email address of the user who forgot their password
	 * @return a redirect to the home page with a flash attribute indicating the result of the password change request
	 */
	@PostMapping("/forgot-password")
	public String ForgotPassword(RedirectAttributes redirectAttributes, @RequestParam String email) {
		try {
			if (passwordChangeService.initiatePasswordChange(email)) {
				redirectAttributes.addFlashAttribute("errorMessage", "Password change email sent successfully.");
				logger.info("Password change initiated for user: {}", email);
			} else {
				redirectAttributes.addFlashAttribute("errorMessage", "Failed to send the password change email.");
				logger.warn("Password change failed for user: {}", email);
			}
			return "redirect:/";
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage", "An error occurred during the password change.");
			logger.error("Error initiating password change for email: {}", email, e);
			return "redirect:/";
		}
	}

	/**
	 * Shows the change password form.
	 *
	 * @param token The password change token.
	 * @param model The model to add attributes.
	 * @return The view name for the change password form ("change-password").
	 */
	@GetMapping("/change-password/{token}")
	public String showChangePasswordForm(@PathVariable String token, Model model) {
	    try {
	        model.addAttribute("token", token);
	        return "change-password";
	    } catch (Exception e) {
	        logger.error("Error while processing change password form.", e);
	        return "redirect:/"; // You may want to redirect to an error page
	    }
	}

	/**
	 * Handles the password change request.
	 *
	 * @param token       The password change token.
	 * @param newPassword The new password.
	 * @return A redirect to the profile page ("/profile").
	 */
	@PostMapping("/change-password")
	public String changePassword(RedirectAttributes redirectAttributes, @RequestParam String token, @RequestParam String newPassword) {
		try {
			newPassword = Encryptor.encrypt(newPassword);
			
			if(passwordChangeService.changePassword(token, newPassword)) {
				logger.info("Password change successful for token: {}", token);
				redirectAttributes.addFlashAttribute("successMessage", "Password changed successfully.");
            } else {
				logger.warn("Password change failed for token: {}", token);
				redirectAttributes.addFlashAttribute("errorMessage", "Failed to change password.");
            }
            return "redirect:/profile";
        } catch (Exception e) {
			logger.error("Error changing password for token: {}", token, e);
			redirectAttributes.addFlashAttribute("errorMessage", "An unexpected error occurred. Please try again later.");
			return "redirect:/profile";
		}
	}

	@GetMapping("/change-password")
	public String changePassword() {
		return "redirect:/";
	}

	@GetMapping("/forgot-password")
	public String forgotPassword() {
		return "redirect:/";
	}
}
