package com.newspaper.controllers;

import com.newspaper.entities.dtos.CategoryDto;
import com.newspaper.entities.dtos.RoleDto;
import com.newspaper.entities.dtos.UserDto;
import com.newspaper.services.CategoryService;
import com.newspaper.services.RegistrationService;
import com.newspaper.services.RoleService;
import com.newspaper.services.UserService;
import com.newspaper.utils.ImageUtils;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Controller responsible for handling user profile-related requests.
 */
@Controller
@RequestMapping("/profile")
public class UserProfileController {

    private static final Logger logger = LoggerFactory.getLogger(UserProfileController.class);

    private final UserService userService;
    private final RegistrationService registrationService;
    private final CategoryService categoryService;
    private final RoleService roleService;

    public UserProfileController(UserService userService, RegistrationService registrationService, CategoryService categoryService, RoleService roleService) {
        this.userService = userService;
        this.registrationService = registrationService;
        this.categoryService = categoryService;
        this.roleService = roleService;
    }

    /**
     * Displays the user profile page.
     *
     * @param session The HttpSession to retrieve user information.
     * @return The name of the view template to render.
     */
    @GetMapping
    public String viewProfile(HttpSession session, Model model) {
        try {
            UserDto user = (UserDto) session.getAttribute("user");

            if (user == null) {
                logger.warn("User not found in session.");
                return "redirect:/";
            }

            List<CategoryDto> categories = categoryService.getAllCategories();
            model.addAttribute("categories", categories);

            logger.info("Viewing profile for user with email: {}", user.getEmail());
            return "profile";
        } catch (Exception e) {
            logger.error("Error while viewing profile.", e);
            return "redirect:/";
        }
    }

    /**
     * Edits the user's email.
     *
     * @param session The HttpSession to retrieve user information.
     * @param email   The new email to set for the user.
     * @return The name of the view template to render.
     */
    @PostMapping("/edit/email")
    public String editEmail(HttpSession session, @RequestParam String email) {
        try {
            UserDto user = (UserDto) session.getAttribute("user");

            if (userService.updateEmail(user.getEmail(), email)) {
                user.setEmail(email);
                session.setAttribute("user", user);
                logger.info("User {} updated email. New email: {}", user.getEmail(), email);
            } else {
                logger.warn("Failed to update user email. User: {}", user.getEmail());
            }
            return "redirect:/profile";
        } catch (Exception e) {
            logger.error("Error while updating user email.", e);
            return "redirect:/profile";
        }
    }

    /**
     * Edits the user's name.
     *
     * @param session   The HttpSession to retrieve user information.
     * @param firstName The new first name to set for the user.
     * @param lastName  The new last name to set for the user.
     * @return The name of the view template to render.
     */
    @PostMapping("/edit/name")
    public String editName(HttpSession session, @RequestParam String firstName, @RequestParam String lastName) {
        try {
            UserDto user = (UserDto) session.getAttribute("user");

            String name = registrationService.processFullName(firstName, lastName);

            if (userService.updateName(user.getEmail(), name)) {
                user.setName(name);
                session.setAttribute("user", user);
                logger.info("User {} updated name. New name: {}", user.getEmail(), name);
            } else {
                logger.warn("Failed to update user name. User: {}", user.getEmail());
            }
            return "redirect:/profile";
        } catch (Exception e) {
            logger.error("Error while updating user name.", e);
            return "redirect:/profile";
        }
    }

    /**
     * Edits the user's phone number.
     *
     * @param session     The HttpSession to retrieve user information.
     * @param phoneNumber The new phone number to set for the user.
     * @return The name of the view template to render.
     */
    @PostMapping("/edit/phone")
    public String editPhone(HttpSession session, @RequestParam String phoneNumber) {
        try {
            UserDto user = (UserDto) session.getAttribute("user");

            String phone = registrationService.processPhoneNumber(phoneNumber);

            if (userService.updatePhone(user.getEmail(), phone)) {
                user.setPhoneNumber(phone);
                session.setAttribute("user", user);
                logger.info("User {} updated phone number. New phone number: {}", user.getEmail(), phone);
            } else {
                logger.warn("Failed to update user phone number. User: {}", user.getEmail());
            }
            return "redirect:/profile";
        } catch (Exception e) {
            logger.error("Error while updating user phone number.", e);
            return "redirect:/profile";
        }
    }

    /**
     * Edits the user's profile picture.
     *
     * @param session The HttpSession to retrieve user information.
     * @param file    The new profile picture file.
     * @return The name of the view template to render.
     */
    @PostMapping("/edit/picture")
    public String editPicture(HttpSession session, @RequestPart("profilePicture") MultipartFile file) {
        try {
            UserDto user = (UserDto) session.getAttribute("user");

            if (userService.updateProfilePicture(user.getEmail(), file)) {
                String convertedImage = ImageUtils.convertToBase64(file.getBytes());
                user.setProfilePicture(convertedImage);
                session.setAttribute("user", user);
                logger.info("User {} updated profile picture.", user.getEmail());
            } else {
                logger.warn("Failed to update user profile picture. User: {}", user.getEmail());
            }
            return "redirect:/profile";
        } catch (Exception e) {
            logger.error("Error while updating user profile picture.", e);
            return "redirect:/profile";
        }
    }

    /**
     * Displays the delete confirmation page.
     *
     * @param session The HttpSession to retrieve user information.
     * @return The name of the view template to render.
     */
    @GetMapping("/delete/confirm")
    public String showDeleteConfirmation(HttpSession session) {
        try {
            UserDto user = (UserDto) session.getAttribute("user");

            if (user != null) {
                RoleDto role = roleService.getRoleByName("ADMIN");
                if (user.getRole().getId() == role.getId()) {
                    logger.warn("Admin {} attempted to access the delete confirmation page.", user.getEmail());
                    return "redirect:/";
                }
                logger.info("User {} accessed the delete confirmation page.", user.getEmail());
                return "delete-confirmation";
            } else {
                logger.warn("User not found in session.");
                return "redirect:/";
            }
        } catch (Exception e) {
            logger.error("Error while processing delete confirmation page.", e);
            return "redirect:/";
        }
    }

    /**
     * Deletes the user account.
     *
     * @param session The HttpSession to retrieve user information.
     * @return The name of the view template to render.
     */
    @PostMapping("/delete")
    public String deleteUser(HttpSession session) {
        try {
            UserDto user = (UserDto) session.getAttribute("user");

            if (userService.deleteUser(user.getEmail())) {
                session.invalidate();
                logger.info("User {} deleted their account successfully.", user.getEmail());
                return "redirect:/";
            } else {
                logger.warn("Failed to delete account for user: {}", user.getEmail());
                return "redirect:/profile";
            }
        } catch (Exception e) {
            logger.error("Error while deleting account for user.", e);
            return "redirect:/profile";
        }
    }
}
