package com.newspaper.services;

import com.newspaper.entities.dtos.ArticleDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.newspaper.entities.dtos.UserDto;

/**
 * Service interface for administrative operations.
 */
public interface AdminService {

	/**
	 * Retrieves a paginated list of {@link UserDto} objects.
	 *
	 * @param pageable The pagination information.
	 * @return A paginated list of {@link UserDto}.
	 */
	Page<UserDto> getUserDtosPage(Pageable pageable);


	/**
	 * Retrieves a paginated list of {@link ArticleDto} objects with associated user information.
	 *
	 * @param pageable The pagination information.
	 * @return A paginated list of {@link ArticleDto}.
	 */
	Page<ArticleDto> getArticleDtosPage(Pageable pageable);

    Page<UserDto> searchUsers(String query, Pageable pageable);
}
