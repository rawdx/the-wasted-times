package com.newspaper.entities.dtos;

import com.newspaper.entities.ArticlePriority;
import com.newspaper.entities.ArticleStatus;
import com.newspaper.entities.Category;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Data Transfer Object (DTO) representing an article.
 * This class is used to transfer article-related information between different layers of the application.
 */
public class ArticleDto {
	
    private long id;
    private String title;
    private String subtitle;
    private String content;
    private String image;
    private LocalDateTime creationDate;
    private LocalDateTime publicationDate;
    private ArticleStatus status;
    private ArticlePriority priority;
    private UserDto user;
    private Category category;
    private List<CommentDto> comments;

    //Constructors

    public ArticleDto() {
    	super();
    }

    /**
     * Constructor for creating an ArticleDto with essential information.
     *
     * @param title         The title of the article.
     * @param subtitle      The subtitle or brief description of the article.
     * @param content       The main content of the article.
     * @param image         The image associated with the article.
     * @param creationDate  The date and time when the article was created.
     * @param status        The status of the article (e.g., draft, published).
     */
    public ArticleDto(String title, String subtitle, String content, String image, LocalDateTime creationDate, ArticleStatus status) {
        this.title = title;
        this.subtitle = subtitle;
        this.content = content;
        this.image = image;
        this.creationDate = creationDate;
        this.status = status;
    }

    // Getters and setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDateTime publicationDate) {
        this.publicationDate = publicationDate;
    }

    public ArticleStatus getStatus() {
        return status;
    }

    public void setStatus(ArticleStatus status) {
        this.status = status;
    }

    public ArticlePriority getPriority() {
        return priority;
    }

    public void setPriority(ArticlePriority priority) {
        this.priority = priority;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<CommentDto> getComments() {
        return comments;
    }

    public void setComments(List<CommentDto> comments) {
        this.comments = comments;
    }
}
