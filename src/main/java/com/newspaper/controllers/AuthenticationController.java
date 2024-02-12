package com.newspaper.controllers;

import com.newspaper.utils.ImageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.newspaper.entities.UserRole;
import com.newspaper.entities.dtos.UserDto;
import com.newspaper.services.LoginService;
import com.newspaper.services.RegistrationService;
import com.newspaper.services.VerificationService;
import com.newspaper.utils.Encryptor;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.multipart.MultipartFile;

/**
 * Controller responsible for handling user authentication-related requests.
 */
@Controller
public class AuthenticationController {

	private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

	private final RegistrationService registrationService;
	private final LoginService loginService;

	final VerificationService verificationService;

	public AuthenticationController(RegistrationService registrationService, LoginService loginService,
			VerificationService verificationService) {
		this.registrationService = registrationService;
		this.loginService = loginService;
		this.verificationService = verificationService;
	}

	/**
	 * Handles the signup request and registers a new user.
	 *
	 * @param session     The HttpSession for storing user login status.
	 * @param email       The email address of the user.
	 * @param password    The password of the user.
	 * @param firstName   The first name of the user.
	 * @param lastName    The last name of the user.
	 * @param phoneNumber The phone number of the user.
	 * @param file        The profile picture file.
	 * @return A redirect to the home page ("/").
	 */
	@PostMapping("/signup")
	public String signup(HttpSession session, @RequestParam String email, @RequestParam String password, @RequestParam String firstName,
						 @RequestParam String lastName, @RequestParam String phoneNumber, @RequestParam("profilePicture") MultipartFile file) {
		try {
			password = Encryptor.encrypt(password);

			String name = registrationService.processFullName(firstName, lastName);
			phoneNumber = registrationService.processPhoneNumber(phoneNumber);
			String picture = ImageUtils.convertToBase64(file.getBytes());

			UserDto user = new UserDto(email, password, name, phoneNumber, picture, false, UserRole.USER);

			if (registrationService.registerUser(user)) {
				session.setAttribute("user", user);
				logger.info("User signed up successfully: {}", email);
            } else {
				logger.warn("User registration failed for email: {}", email);
            }
            return "redirect:/";
        } catch (Exception e) {
			logger.error("Error during signup", e);
			return "redirect:/";
		}
	}

	/**
	 * Handles the login request and logs in the user.
	 *
	 * @param email    The email address of the user.
	 * @param password The password of the user.
	 * @param session  The HttpSession for storing user login status.
	 * @return A redirect to the home page ("/") after login.
	 */
	@PostMapping("/login")
	public String login(@RequestParam String email, @RequestParam String password, HttpSession session) {
		try {
			if (loginService.loginUser(email, password)) {
				session.setAttribute("user", loginService.getUserDetails(email));
				logger.info("User logged in successfully: {}", email);
			} else
				logger.warn("Failed to login user: {}", email);
			return "redirect:/";
		} catch (Exception e) {
			logger.error("Error during login", e);
			return "redirect:/";
		}
	}

	/**
	 * Handles the logout request and invalidates the session.
	 *
	 * @param session  The HttpSession to be invalidated.
	 * @return A redirect to the home page ("/").
	 */
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		try {
				session.invalidate();
				logger.info("User logged out successfully.");
			return "redirect:/";
		} catch (Exception e) {
			logger.error("Error during logout", e);
			return "redirect:/";
		}
	}

	@GetMapping("login")
	public String login() {
		return "redirect:/";
	}

	@GetMapping("signup")
	public String signup() {
		return "redirect:/";
	}
}
