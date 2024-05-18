package com.newspaper.controllers;

import java.time.LocalDateTime;
import java.util.List;

import com.newspaper.entities.ArticleStatus;
import com.newspaper.entities.UserRole;
import com.newspaper.entities.dtos.CommentDto;
import com.newspaper.entities.dtos.UserDto;
import com.newspaper.services.CommentService;
import com.newspaper.utils.ImageUtils;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.newspaper.entities.dtos.ArticleDto;
import com.newspaper.entities.dtos.CategoryDto;
import com.newspaper.services.ArticleService;
import com.newspaper.services.CategoryService;
import org.springframework.web.multipart.MultipartFile;

/**
 * Controller responsible for handling article-related operations.
 */
@Controller
public class ArticleController {

	private final Logger logger = LoggerFactory.getLogger(ArticleController.class);
	
    private final ArticleService articleService;
	private final CategoryService categoryService;
    private final CommentService commentService;
    
    public ArticleController(ArticleService articleService, CategoryService categoryService, CommentService commentService) {
    	this.articleService = articleService;
    	this.categoryService = categoryService;
        this.commentService = commentService;
    }

    /**
     * Displays the writing page for users with the role of WRITER.
     *
     * @param session The HttpSession for checking user role.
     * @return The view name for the writing page ("write") or redirects to home if not authorized.
     */
    @GetMapping("/write")
    public String showWritingPage(HttpSession session, Model model) {
        try {
            UserDto user = (UserDto) session.getAttribute("user");
            if (user == null) {
                logger.warn("User not found in session.");
                return "redirect:/";
            }

            if (user.getRole() != UserRole.WRITER) {
                logger.warn("Non-writer user attempted to access writing page: {}", user.getEmail());
                return "redirect:/";
            }

            List<CategoryDto> categories = categoryService.getAllCategories();
            model.addAttribute("categories", categories);

            return "write";
        } catch (Exception e) {
            logger.error("Error during writing page access", e);
            return "redirect:/";
        }
    }

    /**
     * Handles the creation of a new article by a WRITER user.
     *
     * @param session The HttpSession for retrieving the writer user.
     * @param title   The title of the article.
     * @param subtitle The subtitle of the article.
     * @param content The content of the article.
     * @param file    The image file associated with the article.
     * @return A redirect to the writing page ("/write") after article creation.
     */
    @PostMapping("/write/create")
    public String createArticle(HttpSession session, Model model, @RequestParam String title, @RequestParam String subtitle, @RequestParam String content, @RequestPart("image") MultipartFile file) {
        try {
            UserDto writer = (UserDto) session.getAttribute("user");

            ArticleDto article = new ArticleDto(title, subtitle, content, ImageUtils.convertToBase64(file.getBytes()), LocalDateTime.now(), ArticleStatus.PENDING);

            if (articleService.createArticle(article, writer.getEmail())) {
                logger.info("Article created successfully by user {}", writer.getEmail());
                model.addAttribute("successMessage", "Article created successfully.");
            } else{
                logger.warn("Failed to create article by user {}", writer.getEmail());
                model.addAttribute("errorMessage", "Failed to create article. Please try again later.");

            }
            return "redirect:/write";
        } catch (Exception e) {
            logger.error("Error creating article", e);
            model.addAttribute("errorMessage", "An unexpected error occurred. Please try again later.");
            return "redirect:/write";
        }
    }

    /**
     * Displays the detailed view of a specific article.
     *
     * @param id    The ID of the article to view.
     * @param model The model for adding attributes.
     * @return The view name for the article details page ("article") or redirects to home if not found.
     */
    @GetMapping("/article/{id}")
    public String viewArticle(@PathVariable long id, Model model) {
        try {
            List<CategoryDto> categories = categoryService.getAllCategories();
            model.addAttribute("categories", categories);

            List<CommentDto> comments = commentService.getAllComments();
            model.addAttribute("comments", comments);

            ArticleDto article = articleService.getArticleById(id);

            if (article != null) {
                model.addAttribute("article", article);
                return "article";
            } else {
                return "redirect:/";
            }
        } catch (Exception e) {
            logger.error("Error viewing article with ID: {}", id, e);
            return "redirect:/";
        }
    }
}
