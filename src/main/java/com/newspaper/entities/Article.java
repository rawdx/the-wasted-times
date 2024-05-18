package com.newspaper.entities;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;

/**
 * Entity representing an article in the application.
 * This class is mapped to the "articles" table in the database.
 */
@Entity
@Table(name = "articles")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_article", nullable = false)
    private long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "subtitle", nullable = false)
    private String subtitle;

    @Column(name = "content", columnDefinition = "TEXT", nullable = false)
    private String content;

	@Column(name = "image")
	private byte[] image;

    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;

    @Column(name = "publication_date")
    private LocalDateTime publicationDate;

    @Column(name = "status", nullable = false)
    private ArticleStatus status;

    @Column(name = "priority")
    private ArticlePriority priority;

    @ManyToOne()
    @JoinColumn(name = "writer_id", foreignKey = @ForeignKey(name = "fk_article_writer"))
    private User user;

    @ManyToOne()
    @JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "fk_article_category"))
    private Category category;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    //Constructors

    public Article() {
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

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
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
        if (ArticleStatus.PENDING.equals(status) || ArticleStatus.DECLINED.equals(status)) {
            this.priority = null;
        } else {
            this.priority = priority;
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        if (ArticleStatus.PENDING.equals(status) || ArticleStatus.DECLINED.equals(status)) {
        	this.category = null;
        } else {
            this.category = category;
        }
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
