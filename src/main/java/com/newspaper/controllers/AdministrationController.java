package com.newspaper.controllers;

import java.time.LocalDateTime;
import java.util.List;

import com.newspaper.entities.ArticlePriority;
import com.newspaper.entities.ArticleStatus;
import com.newspaper.entities.dtos.ArticleDto;
import com.newspaper.entities.dtos.CategoryDto;
import com.newspaper.services.ArticleService;
import com.newspaper.services.CategoryService;
import com.newspaper.utils.ImageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.newspaper.entities.UserRole;
import com.newspaper.entities.dtos.UserDto;
import com.newspaper.services.AdminService;
import com.newspaper.services.RegistrationService;
import com.newspaper.services.UserService;
import com.newspaper.utils.Encryptor;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.multipart.MultipartFile;

/**
 * Controller for handling admin-related operations.
 */
@Controller
@RequestMapping("/admin")
public class AdministrationController {

	private static final Logger logger = LoggerFactory.getLogger(AdministrationController.class);

	private final AdminService adminService;
	private final UserService userService;
	private final RegistrationService registrationService;
	private final ArticleService articleService;
	private final CategoryService categoryService;

	public AdministrationController(AdminService adminService, UserService userService,
			RegistrationService registrationService, ArticleService articleService, CategoryService categoryService) {
		this.adminService = adminService;
		this.userService = userService;
		this.registrationService = registrationService;
		this.articleService = articleService;
		this.categoryService = categoryService;
	}

	/**
	 * Displays the admin users page.
	 *
	 * @param session The HttpSession for checking user role.
	 * @param model   The model for adding attributes.
	 * @param page    The current page number.
	 * @return The view name for admin users page ("admin-users").
	 */
	@GetMapping("/users")
	public String showUsers(HttpSession session, Model model,
			@RequestParam(name = "page", defaultValue = "1") int page) {
		try {
			if (isUserAdmin(session)) {
				logger.warn("Non-admin user attempted to access admin users page.");
				return "redirect:/";
			}

			int pageSize = 10;
			Page<UserDto> userPage = adminService.getUserDtosPage(PageRequest.of(page - 1, pageSize));

			int totalPages = userPage.getTotalPages();
			List<UserDto> users = userPage.getContent();

			model.addAttribute("users", users);
			model.addAttribute("totalPages", totalPages);
			model.addAttribute("currentPage", page);

			List<CategoryDto> categories = categoryService.getAllCategories();
			model.addAttribute("categories", categories);

			return "admin-users";
		} catch (Exception e) {
			logger.error("Error while attempting to access admin users page.", e);
			return "redirect:/";
		}
	}

	/**
	 * Handles the creation of a new user by an admin.
	 *
	 * @param session     The HttpSession for checking admin.
	 * @param email       The email of the new user.
	 * @param password    The password of the new user.
	 * @param firstName   The first name of the new user.
	 * @param lastName    The last name of the new user.
	 * @param phoneNumber The phone number of the new user.
	 * @param file        The profile picture file.
	 * @param isVerified  Whether the new user is verified.
	 * @param role        The role of the new user.
	 * @return A redirect to the admin users page.
	 */
	@PostMapping("/users/create")
	public String createUser(HttpSession session, @RequestParam String email, @RequestParam String password,
			@RequestParam String firstName, @RequestParam String lastName, @RequestParam String phoneNumber,
			@RequestParam("profilePicture") MultipartFile file,
			@RequestParam(required = false, defaultValue = "false") boolean isVerified, @RequestParam UserRole role) {
		try {
			password = Encryptor.encrypt(password);

			UserDto admin = (UserDto) session.getAttribute("user");

			String name = registrationService.processFullName(firstName, lastName);
			String phone = registrationService.processPhoneNumber(phoneNumber);
			String picture = ImageUtils.convertToBase64(file.getBytes());

			UserDto userDto = new UserDto(email, password, name, phone, picture, isVerified, role);

			if (registrationService.registerUser(userDto)) {
				logger.info("User {} created successfully by {} - Name: {}, Phone: {}, Role: {}", email,
						admin.getEmail(), name, phone, role);
			} else {
				logger.warn("User creation by {} failed for email: {}", admin.getEmail(), email);
			}
			return "redirect:/admin/users";
		} catch (Exception e) {
			logger.error("Error creating user: {}", email, e);
			return "redirect:/admin/users";
		}
	}

	/**
	 * Handles the update of user information by an admin.
	 *
	 * @param session    The HttpSession for checking admin.
	 * @param email      The email of the user to update.
	 * @param isVerified Whether the user is verified.
	 * @param role       The new role of the user.
	 * @return A redirect to the admin users page.
	 */
	@PostMapping("/users/update")
	public String updateUser(HttpSession session, @RequestParam String email,
			@RequestParam(required = false, defaultValue = "false") boolean isVerified, @RequestParam String role) {
		try {
			UserDto admin = (UserDto) session.getAttribute("user");

			if (!admin.getEmail().equals(email)) {

				if (userService.updateUser(email, isVerified, UserRole.valueOf(role))) {
					logger.info("User {} updated successfully by {} - Role: {}, Verified: {}", email, admin.getEmail(),
							role, isVerified);
				} else {
					logger.warn("User update failed for {} by {}.", email, admin.getEmail());
				}
			} else {
				logger.warn("User {} attempted to update themselves.", email);
			}
			return "redirect:/admin/users";
		} catch (Exception e) {
			logger.error("Error updating user: {}", email, e);
			return "redirect:/admin/users";
		}
	}

	/**
	 * Handles the deletion of a user by an admin.
	 *
	 * @param session The HttpSession for checking admin.
	 * @param email   The email of the user to delete.
	 * @return A redirect to the admin users page.
	 */
	@PostMapping("/users/delete")
	public String deleteUser(HttpSession session, @RequestParam String email) {
		try {
			UserDto admin = (UserDto) session.getAttribute("user");

			if (!admin.getEmail().equals(email)) {
				if (userService.deleteUser(email)) {
					logger.info("User {} deleted successfully by {}.", email, admin.getEmail());
				} else {
					logger.warn("User deletion failed for {} by {}.", email, admin.getEmail());
				}
			} else {
				logger.warn("User {} attempted to delete themselves.", email);
			}
			return "redirect:/admin/users";
		} catch (Exception e) {
			logger.error("Error deleting user: {}", email, e);
			return "redirect:/admin/users";
		}
	}

	/**
	 * Displays the admin articles page.
	 *
	 * @param session The HttpSession for checking user role.
	 * @param model   The model for adding attributes.
	 * @param page    The current page number.
	 * @return The view name for admin articles page ("admin-articles").
	 */
	@GetMapping("/articles")
	public String showArticles(HttpSession session, Model model,
			@RequestParam(name = "page", defaultValue = "1") int page) {
		try {
			if (isUserAdmin(session)) {
				logger.warn("Non-admin user attempted to access admin articles page.");
				return "redirect:/";
			}

			int pageSize = 10;
			Page<ArticleDto> articlePage = adminService.getArticleDtosPage(PageRequest.of(page - 1, pageSize));

			int totalPages = articlePage.getTotalPages();
			List<ArticleDto> articles = articlePage.getContent();

			model.addAttribute("articles", articles);
			model.addAttribute("totalPages", totalPages);
			model.addAttribute("currentPage", page);

			List<CategoryDto> categories = categoryService.getAllCategories();
			model.addAttribute("categories", categories);

			return "admin-articles";
		} catch (Exception e) {
			logger.error("Error while attempting to access admin articles page.", e);
			return "redirect:/";
		}
	}

	/**
	 * Handles the update of article information by an admin.
	 *
	 * @param session    The HttpSession for checking admin.
	 * @param id         The ID of the article to update.
	 * @param status     The new status of the article.
	 * @param priority   The new priority of the article.
	 * @param categoryId The new category ID of the article.
	 * @return A redirect to the admin articles page.
	 */
	@PostMapping("/articles/update")
	public String updateArticle(HttpSession session, @RequestParam long id, @RequestParam String status,
			@RequestParam String priority, @RequestParam long categoryId) {
		try {
			UserDto admin = (UserDto) session.getAttribute("user");

			if (articleService.updateArticle(id, ArticleStatus.valueOf(status), ArticlePriority.valueOf(priority),
					categoryId, LocalDateTime.now())) {
				logger.info(
						"Article with ID '{}' updated successfully by {}. Status: {}, Priority: {}, Category ID: {}",
						id, admin.getEmail(), status, priority, categoryId);
			} else {
				logger.warn("Failed to update article with ID {}. Updated by {}. Status: {}, Priority: {}", id,
						admin.getEmail(), status, priority);
			}
			return "redirect:/admin/articles";
		} catch (Exception e) {
			logger.error("Error updating article with ID: {}", id, e);
			return "redirect:/admin/articles";
		}
	}

	/**
	 * Handles the deletion of an article by an admin.
	 *
	 * @param session The HttpSession for checking admin.
	 * @param id      The ID of the article to delete.
	 * @return A redirect to the admin articles page.
	 */
	@PostMapping("/articles/delete")
	public String deleteArticle(HttpSession session, @RequestParam long id) {
		try {
			UserDto admin = (UserDto) session.getAttribute("user");

			if (articleService.deleteArticle(id)) {
				logger.info("Article deleted successfully by {}.", admin.getEmail());
			} else {
				logger.warn("Failed to delete article by {}.", admin.getEmail());
			}
			return "redirect:/admin/articles";
		} catch (Exception e) {
			logger.error("Error deleting article.", e);
			return "redirect:/admin/articles";
		}
	}


	/**
	 * Displays the admin categories page.
	 *
	 * @param session The HttpSession for checking user role.
	 * @param model   The model for adding attributes.
	 * @return The view name for admin categories page ("admin-categories").
	 */
	@GetMapping("/categories")
	public String showCategories(HttpSession session, Model model) {
		try {
			if (isUserAdmin(session)) {
				logger.warn("Non-admin user attempted to access admin categories page.");
				return "redirect:/";
			}

			List<CategoryDto> categories = categoryService.getAllCategories();
			model.addAttribute("categories", categories);

			return "admin-categories";
		} catch (Exception e) {
			logger.error("Error while attempting to access admin categories page.", e);
			return "redirect:/";
		}
	}

	/**
	 * Handles the creation of a new category by an admin.
	 *
	 * @param session The HttpSession for checking admin.
	 * @param name    The name of the new category.
	 * @param url     The URL of the new category.
	 * @return A redirect to the admin categories page.
	 */
	@PostMapping("/categories/create")
	public String createCategory(HttpSession session, @RequestParam String name, @RequestParam String url) {
		try {
			UserDto admin = (UserDto) session.getAttribute("user");

			CategoryDto categoryDto = new CategoryDto(name, url);

			if (categoryService.createCategory(categoryDto)) {
				logger.info("Category created successfully by {} - Name: {}, Url: {}", admin.getEmail(), name, url);
			} else {
				logger.warn("Failed to create category by {} - Name: {}, Url: {}", admin.getEmail(), name, url);
			}
			return "redirect:/admin/categories";
		} catch (Exception e) {
			logger.error("Error creating category - Name: {}, Url: {}", name, url, e);
			return "redirect:/admin/categories";
		}
	}

	/**
	 * Handles the update of category information by an admin.
	 *
	 * @param session The HttpSession for checking admin.
	 * @param id      The ID of the category to update.
	 * @param name    The new name of the category.
	 * @param url     The new URL of the category.
	 * @return A redirect to the admin categories page.
	 */
	@PostMapping("/categories/update")
	public String updateCategory(HttpSession session, @RequestParam long id, @RequestParam String name,
			@RequestParam String url) {
		try {
			UserDto admin = (UserDto) session.getAttribute("user");

			if (categoryService.updateCategory(id, name, url)) {
				logger.info("Category with ID '{}' updated successfully by {}. Name: {}, Url: {}", id,
						admin.getEmail(), name, url);
			} else {
				logger.warn("Failed to update category with ID {}. Updated by {}. Name: {}, Url: {}", id,
						admin.getEmail(), name, url);
			}
			return "redirect:/admin/categories";
		} catch (Exception e) {
			logger.error("Error updating category with ID: {}", id, e);
			return "redirect:/admin/categories";
		}
	}

	/**
	 * Handles the deletion of a category by an admin.
	 *
	 * @param session The HttpSession for checking admin.
	 * @param id      The ID of the category to delete.
	 * @return A redirect to the admin categories page.
	 */
	@PostMapping("/categories/delete")
	public String deleteCategory(HttpSession session, @RequestParam long id) {
		try {
			UserDto admin = (UserDto) session.getAttribute("user");

			if (categoryService.deleteCategory(id)) {
				logger.info("Category deleted successfully by {}.", admin.getEmail());
			} else {
				logger.warn("Failed to delete category by {}.", admin.getEmail());
			}
			return "redirect:/admin/categories";
		} catch (Exception e) {
			logger.error("Error deleting category.", e);
			return "redirect:/admin/categories";
		}
	}

	/**
	 * Checks if the logged-in user is an admin.
	 *
	 * @param session The HttpSession to check the user's role.
	 * @return True if the user is an admin, false otherwise.
	 */
	private boolean isUserAdmin(HttpSession session) {
		UserDto user = (UserDto) session.getAttribute("user");
		return user == null || user.getRole() != UserRole.ADMIN;
	}
}
