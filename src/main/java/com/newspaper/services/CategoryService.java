package com.newspaper.services;

import java.util.List;

import com.newspaper.entities.Category;
import com.newspaper.entities.dtos.ArticleDto;
import com.newspaper.entities.dtos.CategoryDto;

/**
 * Service interface for managing categories and related operations.
 */
public interface CategoryService {

	/**
	 * Converts a {@link CategoryDto} object to a {@link Category} entity.
	 *
	 * @param categoryDto The category data transfer object.
	 * @return The converted {@link Category} entity.
	 */
	Category convertToCategoryEntity(CategoryDto categoryDto);

	/**
	 * Converts a {@link Category} entity to a {@link CategoryDto} data transfer object.
	 *
	 * @param category The category entity.
	 * @return The converted {@link CategoryDto}.
	 */
	CategoryDto convertToCategoryDto(Category category);

	/**
	 * Retrieves a list of all categories as {@link CategoryDto}.
	 *
	 * @return A list of {@link CategoryDto}.
	 */
	List<CategoryDto> getAllCategories();


	/**
	 * Retrieves a {@link Category} entity based on its URL.
	 *
	 * @param url The URL of the category.
	 * @return The {@link Category} entity if found, otherwise null.
	 */
	Category getCategoryByUrl(String url);


	/**
	 * Retrieves a list of {@link ArticleDto} objects associated with a category URL.
	 *
	 * @param url The URL of the category.
	 * @return A list of {@link ArticleDto} objects associated with the category.
	 */
	List<ArticleDto> getArticlesByCategoryUrl(String url);

	/**
	 * Creates a new category based on the provided {@link CategoryDto}.
	 *
	 * @param categoryDto The category data transfer object.
	 * @return True if the category is successfully created, false otherwise.
	 */
	boolean createCategory(CategoryDto categoryDto);

	/**
	 * Updates an existing category based on the provided parameters.
	 *
	 * @param id   The ID of the category to update.
	 * @param name The new name for the category.
	 * @param url  The new URL for the category.
	 * @return True if the category is successfully updated, false otherwise.
	 */
	boolean updateCategory(long id, String name, String url);

	/**
	 * Deletes a category based on the provided ID.
	 *
	 * @param id The ID of the category to delete.
	 * @return True if the category is successfully deleted, false otherwise.
	 */
	boolean deleteCategory(long id);
}
