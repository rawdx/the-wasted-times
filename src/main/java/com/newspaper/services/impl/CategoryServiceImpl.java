package com.newspaper.services.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.newspaper.entities.Article;
import com.newspaper.entities.Category;
import com.newspaper.entities.dtos.ArticleDto;
import com.newspaper.entities.dtos.CategoryDto;
import com.newspaper.repositories.CategoryRepository;
import com.newspaper.services.ArticleService;
import com.newspaper.services.CategoryService;

/**
 * Implementation of the {@link CategoryService} interface for category-related operations.
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ArticleService articleService;
	
	public CategoryServiceImpl(CategoryRepository categoryRepository, ArticleService articleService) {
		this.categoryRepository = categoryRepository;
		this.articleService = articleService;
	}
	
	
	@Override
	public Category convertToCategoryEntity(CategoryDto categoryDto) {
		Category category = new Category();

		category.setName(categoryDto.getName());
		category.setUrl(categoryDto.getUrl());

		return category;
	}

	@Override
	public CategoryDto convertToCategoryDto(Category category) {
		CategoryDto categoryDto = new CategoryDto();

		categoryDto.setId(category.getId());
		categoryDto.setName(category.getName());
		categoryDto.setUrl(category.getUrl());

		return categoryDto;
	}
	
	@Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();

        return categories.stream()
                .map(this::convertToCategoryDto)
                .collect(Collectors.toList());
    }
	
	@Override
	public Category getCategoryByUrl(String url) {
		return categoryRepository.findByUrl(url);
	}
	
    @Override
    public List<ArticleDto> getArticlesByCategoryUrl(String url) {
        Category category = getCategoryByUrl(url);

        if (category != null) {
            List<Article> articles = category.getArticles();
            return articleService.convertToArticleDtoList(articles);
        }

        return Collections.emptyList();
    }


    @Override
    public boolean createCategory(CategoryDto categoryDto) {
        try {
            Category categoryEntity = convertToCategoryEntity(categoryDto);

            categoryRepository.save(categoryEntity);

            return categoryRepository.existsById(categoryEntity.getId());
        } catch (Exception e) {
            return false;
        }
    }

	@Override
	public boolean updateCategory(long id, String name, String url) {
		Optional<Category> optionalCategory = categoryRepository.findById(id);

		if (optionalCategory.isPresent()) {
			Category category = optionalCategory.get();

			category.setName(name);
			category.setUrl(url);

			categoryRepository.save(category);

			return categoryRepository.existsById(category.getId());
		} else {
			return false;
		}
	}
	
	@Override
	public boolean deleteCategory(long id) {
		Optional<Category> optionalCategory = categoryRepository.findById(id);
		
		if(optionalCategory.isPresent()) {
			Category category = optionalCategory.get();
			
			categoryRepository.delete(category);
			return true;
		}
		return false;
	}
}
