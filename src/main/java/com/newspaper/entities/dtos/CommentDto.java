package com.newspaper.entities.dtos;

import java.time.LocalDateTime;

public class CommentDto {

    private long id;
    private String content;
    private LocalDateTime creationDate;
    private ArticleDto article;
    private UserDto user;

    public CommentDto() {
    }

    public CommentDto(String content, LocalDateTime creationDate) {
        this.content = content;
        this.creationDate = creationDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public ArticleDto getArticle() {
        return article;
    }

    public void setArticle(ArticleDto article) {
        this.article = article;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}
