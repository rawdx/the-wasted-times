package com.newspaper.repositories;

import com.newspaper.entities.Article;
import com.newspaper.entities.ArticlePriority;
import com.newspaper.entities.ArticleStatus;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for managing {@link Article} entities.
 * Uses Spring Data JPA for data access.
 */
@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    /**
     * Retrieves a paginated list of {@link Article} entities along with their associated users.
     *
     * @param pageable Pagination information.
     * @return A page of {@link Article} entities with associated users.
     */
    @Query("SELECT a FROM Article a JOIN FETCH a.user")
    Page<Article> findAllWithUser(Pageable pageable);

    /**
     * Retrieves a list of {@link Article} entities with a specific status, ordered by publication date in descending order.
     *
     * @param status The status of articles to retrieve.
     * @param limit  The maximum number of articles to return.
     * @return A list of {@link Article} entities with the specified status.
     */
    List<Article> findByStatusOrderByPublicationDateDesc(ArticleStatus status, Limit limit);

    /**
     * Retrieves the top {@link Article} entity with a specific status and priority, ordered by publication date in descending order.
     *
     * @param status   The status of the article to retrieve.
     * @param priority The priority of the article to retrieve.
     * @return An optional {@link Article} entity with the specified status and priority.
     */
    Optional<Article> findTopByStatusAndPriorityOrderByPublicationDateDesc(ArticleStatus status, ArticlePriority priority);

    /**
     * Retrieves a list of {@link Article} entities with a specific status, priority, and ordered by publication date in descending order.
     *
     * @param status   The status of articles to retrieve.
     * @param priority The priority of articles to retrieve.
     * @param limit    The maximum number of articles to return.
     * @return A list of {@link Article} entities with the specified status and priority.
     */
    List<Article> findByStatusAndPriorityOrderByPublicationDateDesc(ArticleStatus status, ArticlePriority priority, Limit limit);
}
