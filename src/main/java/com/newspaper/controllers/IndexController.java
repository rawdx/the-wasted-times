package com.newspaper.controllers;

import com.newspaper.entities.dtos.ArticleDto;
import com.newspaper.entities.dtos.CategoryDto;
import com.newspaper.services.ArticleService;
import com.newspaper.services.CategoryService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

/**
 * Controller for handling requests related to the home page.
 */
@Controller
public class IndexController {

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    private final ArticleService articleService;
    private final CategoryService categoryService;

    public IndexController(ArticleService articleService, CategoryService categoryService) {
        this.articleService = articleService;
        this.categoryService = categoryService;
    }

    /**
     * Displays content on the home page.
     *
     * @param model The model for adding attributes to the view.
     * @return The name of the view template to render.
     */
    @GetMapping("/")
    public String showContent(Model model){
        try{
            model.addAttribute("currentUrl", "/");
            
        	List<CategoryDto> categories = categoryService.getAllCategories();
            model.addAttribute("categories", categories);
            
            Optional<ArticleDto> optionalArticleDto = articleService.getLastHighlightedArticle();
            optionalArticleDto.ifPresent(articleDto -> model.addAttribute("highlightedArticle", articleDto));

            List<ArticleDto> recentArticles = articleService.getRecentArticles(4);
            model.addAttribute("recentArticles", recentArticles);

            List<ArticleDto> featuredArticles = articleService.getFeaturedArticles(6);
            if (featuredArticles != null) {
                model.addAttribute("featuredArticles", featuredArticles);
            }

            return "index";
        } catch (Exception e){
            logger.error("Error while displaying index content.", e);
            return "index";
        }
    }
}
