package com.newspaper.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.newspaper.entities.Category;

/**
 * Repository interface for managing {@link Category} entities.
 * Uses Spring Data JPA for data access.
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {

	/**
	 * Retrieves a {@link Category} entity based on the provided URL.
	 *
	 * @param url The URL to search for.
	 * @return The {@link Category} entity with the specified URL, or null if not found.
	 */
	Category findByUrl(String url);
}
