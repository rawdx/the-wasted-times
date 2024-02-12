package com.newspaper.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.newspaper.entities.dtos.ArticleDto;
import com.newspaper.entities.dtos.CategoryDto;
import com.newspaper.services.CategoryService;

/**
 * Controller responsible for handling category-related operations.
 */
@Controller
public class CategoryController {

	private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

	private final CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

    /**
     * Displays articles belonging to a specific category.
     *
     * @param categoryUrl The URL of the category.
     * @param model       The model for adding attributes.
     * @return The view name for the category page ("category") or redirects to home if an error occurs.
     */
	@GetMapping("/category/{categoryUrl}")
	public String showArticlesByCategory(@PathVariable String categoryUrl, Model model) {
        try {
            List<CategoryDto> categories = categoryService.getAllCategories();
            model.addAttribute("categories", categories);

            List<ArticleDto> articles = categoryService.getArticlesByCategoryUrl(categoryUrl);
            model.addAttribute("articles", articles);
            model.addAttribute("currentUrl", categoryUrl);

            logger.info("Successfully retrieved articles for category url: {}", categoryUrl);
            return "category";
        } catch (Exception e) {
            logger.error("Error while displaying category content.", e);
            return "redirect:/";
        }
	}
}
