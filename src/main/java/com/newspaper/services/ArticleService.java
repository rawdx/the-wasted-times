package com.newspaper.services;

import com.newspaper.entities.Article;
import com.newspaper.entities.ArticlePriority;
import com.newspaper.entities.ArticleStatus;
import com.newspaper.entities.dtos.ArticleDto;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing articles and related operations.
 */
public interface ArticleService {

    /**
     * Converts an {@link ArticleDto} object to an {@link Article} entity.
     *
     * @param articleDto The article data transfer object.
     * @return The converted {@link Article} entity.
     */
    Article convertToArticleEntity(ArticleDto articleDto);

    /**
     * Converts an {@link Article} entity to an {@link ArticleDto} data transfer object.
     *
     * @param article The article entity.
     * @return The converted {@link ArticleDto}.
     */
    ArticleDto convertToArticleDto(Article article);

    /**
     * Creates a new article based on the provided {@link ArticleDto} and user email.
     *
     * @param articleDto The article data transfer object.
     * @param email      The email address of the user creating the article.
     * @return True if the article is successfully created, false otherwise.
     */
    boolean createArticle(ArticleDto articleDto, String email);

    /**
     * Updates an existing article based on the provided parameters.
     *
     * @param id          The ID of the article to update.
     * @param status      The status to set.
     * @param priority    The priority to set.
     * @param categoryId  The ID of the category to set.
     * @param date        The date to set.
     * @return True if the article is successfully updated, false otherwise.
     */
    boolean updateArticle(long id, ArticleStatus status, ArticlePriority priority, long categoryId, LocalDateTime date);

    /**
     * Deletes an article based on the provided ID.
     *
     * @param id The ID of the article to delete.
     * @return True if the article is successfully deleted, false otherwise.
     */
    boolean deleteArticle(long id);

    /**
     * Retrieves the last highlighted article as an optional {@link ArticleDto}.
     *
     * @return An optional {@link ArticleDto} representing the last highlighted article.
     */
    Optional<ArticleDto> getLastHighlightedArticle();

    /**
     * Retrieves a list of recent articles up to the specified count.
     *
     * @param count The maximum number of recent articles to retrieve.
     * @return A list of {@link ArticleDto} representing recent articles.
     */
    List<ArticleDto> getRecentArticles(int count);

    /**
     * Retrieves a list of featured articles up to the specified count.
     *
     * @param count The maximum number of featured articles to retrieve.
     * @return A list of {@link ArticleDto} representing featured articles.
     */
    List<ArticleDto> getFeaturedArticles(int count);

    /**
     * Converts a list of {@link Article} entities to a list of {@link ArticleDto}.
     *
     * @param articles The list of articles to convert.
     * @return A list of {@link ArticleDto}.
     */
	List<ArticleDto> convertToArticleDtoList(List<Article> articles);

    /**
     * Retrieves an {@link ArticleDto} by its ID.
     *
     * @param id The ID of the article.
     * @return The {@link ArticleDto} with the specified ID, or null if not found.
     */
	ArticleDto getArticleById(long id);
}
