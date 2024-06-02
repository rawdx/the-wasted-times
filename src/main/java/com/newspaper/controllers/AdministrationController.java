package com.newspaper.controllers;

import java.time.LocalDateTime;
import java.util.List;

import com.newspaper.entities.ArticlePriority;
import com.newspaper.entities.ArticleStatus;
import com.newspaper.entities.dtos.ArticleDto;
import com.newspaper.entities.dtos.CategoryDto;
import com.newspaper.entities.dtos.RoleDto;
import com.newspaper.services.*;
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

import com.newspaper.entities.dtos.UserDto;
import com.newspaper.utils.Encryptor;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	private final RoleService roleService;

	public AdministrationController(AdminService adminService, UserService userService,
                                    RegistrationService registrationService, ArticleService articleService, CategoryService categoryService, RoleService roleService) {
		this.adminService = adminService;
		this.userService = userService;
		this.registrationService = registrationService;
		this.articleService = articleService;
		this.categoryService = categoryService;
        this.roleService = roleService;
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

			List<RoleDto> roles = roleService.getAllRoles();
			model.addAttribute("roles", roles);

			return "admin-users";
		} catch (Exception e) {
			logger.error("Error while attempting to access admin users page.", e);
			return "redirect:/";
		}
	}

	/**
	 * Handles the search for users by an admin.
	 *
	 * @param session The HttpSession for checking admin.
	 * @param model The Model to hold attributes for the view.
	 * @param query The search query string.
	 * @param page The current page number for pagination (default is 1).
	 * @return The view name for the admin users page.
	 */
	@GetMapping("/users/search")
	public String searchUsers(HttpSession session, Model model,
							  @RequestParam(name = "query") String query,
							  @RequestParam(name = "page", defaultValue = "1") int page) {
		try {
			if (isUserAdmin(session)) {
				logger.warn("Non-admin user attempted to access admin users page.");
				return "redirect:/";
			}

			int pageSize = 10;
			Page<UserDto> userPage = adminService.searchUsers(query, PageRequest.of(page - 1, pageSize));

			int totalPages = userPage.getTotalPages();
			List<UserDto> users = userPage.getContent();

			model.addAttribute("users", users);
			model.addAttribute("totalPages", totalPages);
			model.addAttribute("currentPage", page);
			model.addAttribute("searchQuery", query);

			List<CategoryDto> categories = categoryService.getAllCategories();
			model.addAttribute("categories", categories);

			List<RoleDto> roles = roleService.getAllRoles();
			model.addAttribute("roles", roles);

			return "admin-users";
		} catch (Exception e) {
			logger.error("Error while attempting to search users.", e);
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
	public String createUser(RedirectAttributes redirectAttributes, HttpSession session, @RequestParam String email, @RequestParam String password,
							 @RequestParam String firstName, @RequestParam String lastName, @RequestParam String phoneNumber,
							 @RequestParam("profilePicture") MultipartFile file,
							 @RequestParam(required = false, defaultValue = "false") boolean isVerified, @RequestParam RoleDto role) {
		try {
			password = Encryptor.encrypt(password);

			UserDto admin = (UserDto) session.getAttribute("user");

			String name = registrationService.processFullName(firstName, lastName);
			String phone = registrationService.processPhoneNumber(phoneNumber);
			String picture = ImageUtils.convertToBase64(file.getBytes());

			UserDto userDto = new UserDto(email, password, name, phone, picture, isVerified, role);

			if (registrationService.registerUser(userDto)) {
				logger.info("User {} created successfully by {} - Name: {}, Phone: {}, Role: {}", email,
						admin.getEmail(), name, phone, role.getName());
				redirectAttributes.addFlashAttribute("successMessage", "User created successfully.");
			} else {
				logger.warn("User creation by {} failed for email: {}", admin.getEmail(), email);
				redirectAttributes.addFlashAttribute("errorMessage", "User creation failed for email: " + email);
			}
			return "redirect:/admin/users";
		} catch (Exception e) {
			logger.error("Error creating user: {}", email, e);
			redirectAttributes.addFlashAttribute("errorMessage", "Error creating user: " + email);
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
	public String updateUser(RedirectAttributes redirectAttributes, HttpSession session, @RequestParam String email,
			@RequestParam(required = false, defaultValue = "false") boolean isVerified, @RequestParam RoleDto role) {
		try {
			UserDto admin = (UserDto) session.getAttribute("user");

			if (!admin.getEmail().equals(email)) {

				if (userService.updateUser(email, isVerified, role)) {
					logger.info("User {} updated successfully by {} - Role: {}, Verified: {}", email, admin.getEmail(),
							role.getName(), isVerified);
					redirectAttributes.addFlashAttribute("successMessage", "User updated successfully.");
				} else {
					logger.warn("User update failed for {} by {}.", email, admin.getEmail());
					redirectAttributes.addFlashAttribute("errorMessage", "User update failed for email: " + email);

				}
			} else {
				logger.warn("User {} attempted to update themselves.", email);
				redirectAttributes.addFlashAttribute("errorMessage", "You cannot update your own account.");
			}
			return "redirect:/admin/users";
		} catch (Exception e) {
			logger.error("Error updating user: {}", email, e);
			redirectAttributes.addFlashAttribute("errorMessage", "Error updating user: " + email);
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
	public String deleteUser(RedirectAttributes redirectAttributes, HttpSession session, @RequestParam String email) {
		try {
			UserDto admin = (UserDto) session.getAttribute("user");

			if (!admin.getEmail().equals(email)) {
				if (userService.deleteUser(email)) {
					logger.info("User {} deleted successfully by {}.", email, admin.getEmail());
					redirectAttributes.addFlashAttribute("successMessage", "User deleted successfully.");
				} else {
					logger.warn("User deletion failed for {} by {}.", email, admin.getEmail());
					redirectAttributes.addFlashAttribute("errorMessage", "User deletion failed for email: " + email);
				}
			} else {
				logger.warn("User {} attempted to delete themselves.", email);
				redirectAttributes.addFlashAttribute("errorMessage", "You cannot delete your own account.");
			}
			return "redirect:/admin/users";
		} catch (Exception e) {
			logger.error("Error deleting user: {}", email, e);
			redirectAttributes.addFlashAttribute("errorMessage", "Error deleting user: " + email);
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
	public String updateArticle(RedirectAttributes redirectAttributes, HttpSession session, @RequestParam long id, @RequestParam String status,
			@RequestParam String priority, @RequestParam long categoryId) {
		try {
			UserDto admin = (UserDto) session.getAttribute("user");

			if (articleService.updateArticle(id, ArticleStatus.valueOf(status), ArticlePriority.valueOf(priority),
					categoryId, LocalDateTime.now())) {
				logger.info(
						"Article with ID '{}' updated successfully by {}. Status: {}, Priority: {}, Category ID: {}",
						id, admin.getEmail(), status, priority, categoryId);
				redirectAttributes.addFlashAttribute("successMessage", "Article updated successfully.");
			} else {
				logger.warn("Failed to update article with ID {}. Updated by {}. Status: {}, Priority: {}", id,
						admin.getEmail(), status, priority);
				redirectAttributes.addFlashAttribute("errorMessage", "Failed to update article.");
			}
			return "redirect:/admin/articles";
		} catch (Exception e) {
			logger.error("Error updating article with ID: {}", id, e);
			redirectAttributes.addFlashAttribute("errorMessage", "Error updating article.");
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
	public String deleteArticle(RedirectAttributes redirectAttributes, HttpSession session, @RequestParam long id) {
		try {
			UserDto admin = (UserDto) session.getAttribute("user");

			if (articleService.deleteArticle(id)) {
				logger.info("Article deleted successfully by {}.", admin.getEmail());
				redirectAttributes.addFlashAttribute("successMessage", "Article deleted successfully.");
			} else {
				logger.warn("Failed to delete article by {}.", admin.getEmail());
				redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete article.");
			}
			return "redirect:/admin/articles";
		} catch (Exception e) {
			logger.error("Error deleting article.", e);
			redirectAttributes.addFlashAttribute("errorMessage", "Error deleting article.");
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
	public String createCategory(RedirectAttributes redirectAttributes, HttpSession session, @RequestParam String name, @RequestParam String url) {
		try {
			UserDto admin = (UserDto) session.getAttribute("user");

			CategoryDto categoryDto = new CategoryDto(name, url);

			if (categoryService.createCategory(categoryDto)) {
				logger.info("Category created successfully by {} - Name: {}, Url: {}", admin.getEmail(), name, url);
				redirectAttributes.addFlashAttribute("successMessage", "Category created successfully.");
			} else {
				logger.warn("Failed to create category by {} - Name: {}, Url: {}", admin.getEmail(), name, url);
				redirectAttributes.addFlashAttribute("errorMessage", "Failed to create category.");
			}
			return "redirect:/admin/categories";
		} catch (Exception e) {
			logger.error("Error creating category - Name: {}, Url: {}", name, url, e);
			redirectAttributes.addFlashAttribute("errorMessage", "Error creating category.");
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
	public String updateCategory(RedirectAttributes redirectAttributes, HttpSession session, @RequestParam long id, @RequestParam String name,
			@RequestParam String url) {
		try {
			UserDto admin = (UserDto) session.getAttribute("user");

			if (categoryService.updateCategory(id, name, url)) {
				logger.info("Category with ID '{}' updated successfully by {}. Name: {}, Url: {}", id,
						admin.getEmail(), name, url);
				redirectAttributes.addFlashAttribute("successMessage", "Category updated successfully.");

			} else {
				logger.warn("Failed to update category with ID {}. Updated by {}. Name: {}, Url: {}", id,
						admin.getEmail(), name, url);
				redirectAttributes.addFlashAttribute("errorMessage", "Failed to update category.");
			}
			return "redirect:/admin/categories";
		} catch (Exception e) {
			logger.error("Error updating category with ID: {}", id, e);
			redirectAttributes.addFlashAttribute("errorMessage", "Error updating category.");
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
	public String deleteCategory(RedirectAttributes redirectAttributes, HttpSession session, @RequestParam long id) {
		try {
			UserDto admin = (UserDto) session.getAttribute("user");

			if (categoryService.deleteCategory(id)) {
				logger.info("Category deleted successfully by {}.", admin.getEmail());
				redirectAttributes.addFlashAttribute("successMessage", "Category deleted successfully.");
			} else {
				logger.warn("Failed to delete category by {}.", admin.getEmail());
				redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete category.");
			}
			return "redirect:/admin/categories";
		} catch (Exception e) {
			logger.error("Error deleting category.", e);
			redirectAttributes.addFlashAttribute("errorMessage", "Error deleting category.");
			return "redirect:/admin/categories";
		}
	}

	@GetMapping("/roles")
	public String showRoles(HttpSession session, Model model) {
		try {
			if (isUserAdmin(session)) {
				logger.warn("Non-admin user attempted to access admin roles page.");
				return "redirect:/";
			}

			List<RoleDto> roles = roleService.getAllRoles();
			model.addAttribute("roles", roles);

			List<CategoryDto> categories = categoryService.getAllCategories();
			model.addAttribute("categories", categories);

			return "admin-roles";
		} catch (Exception e) {
			logger.error("Error while attempting to access admin roles page.", e);
			return "redirect:/";
		}

	}

//	@PostMapping("/roles/create")
//	public String createRole(RedirectAttributes redirectAttributes, HttpSession session, @RequestParam String name) {
//		try {
//			UserDto admin = (UserDto) session.getAttribute("user");
//
//			RoleDto roleDto = new RoleDto(name);
//
//			if (roleService.createRole(roleDto)) {
//				logger.info("Role created successfully by {} - Name: {}", admin.getEmail(), name);
//				redirectAttributes.addFlashAttribute("successMessage", "Role created successfully.");
//			} else {
//				logger.warn("Failed to create role by {} - Name: {}", admin.getEmail(), name);
//				redirectAttributes.addFlashAttribute("errorMessage", "Failed to create role.");
//			}
//			return "redirect:/admin/roles";
//		} catch (Exception e) {
//			logger.error("Error creating category - Name: {}", name, e);
//			redirectAttributes.addFlashAttribute("errorMessage", "Error creating role.");
//			return "redirect:/admin/roles";
//		}
//	}

	/**
	 * Checks if the logged-in user is an admin.
	 *
	 * @param session The HttpSession to check the user's role.
	 * @return True if the user is an admin, false otherwise.
	 */
	private boolean isUserAdmin(HttpSession session) {
		UserDto user = (UserDto) session.getAttribute("user");
        RoleDto role = roleService.getRoleByName("ADMIN");

		return user == null || user.getRole().getId() != role.getId();
	}
}
