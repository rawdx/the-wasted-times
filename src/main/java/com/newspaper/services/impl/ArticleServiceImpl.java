package com.newspaper.services.impl;

import com.newspaper.entities.*;
import com.newspaper.entities.dtos.ArticleDto;
import com.newspaper.repositories.ArticleRepository;
import com.newspaper.repositories.CategoryRepository;
import com.newspaper.repositories.UserRepository;
import com.newspaper.services.ArticleService;
import com.newspaper.services.UserService;
import com.newspaper.utils.ImageUtils;
import org.springframework.data.domain.Limit;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementation of the {@link ArticleService} interface for article-related operations.
 */
@Service
public class ArticleServiceImpl implements ArticleService {

	private final ArticleRepository articleRepository;
	private final UserRepository userRepository;
	private final UserService userService;
	private final CategoryRepository categoryRepository;

	public ArticleServiceImpl(ArticleRepository articleRepository, UserRepository userRepository,
			UserService userService, CategoryRepository categoryRepository) {
		this.articleRepository = articleRepository;
		this.userRepository = userRepository;
		this.userService = userService;
		this.categoryRepository = categoryRepository;
	}

	@Override
	public Article convertToArticleEntity(ArticleDto articleDto) {
		Article article = new Article();

		article.setCreationDate(articleDto.getCreationDate());
		article.setTitle(articleDto.getTitle());
		article.setSubtitle(articleDto.getSubtitle());
		article.setContent(articleDto.getContent());
		article.setImage(ImageUtils.convertToByteArray(articleDto.getImage()));
		article.setStatus(articleDto.getStatus());

		return article;
	}

	@Override
	public ArticleDto convertToArticleDto(Article article) {
		ArticleDto articleDto = new ArticleDto();

		articleDto.setId(article.getId());
		articleDto.setCreationDate(article.getCreationDate());
		articleDto.setPublicationDate(article.getPublicationDate());
		articleDto.setTitle(article.getTitle());
		articleDto.setSubtitle(article.getSubtitle());
		articleDto.setContent(article.getContent());
		articleDto.setImage(ImageUtils.convertToBase64(article.getImage()));
		articleDto.setStatus(article.getStatus());
		articleDto.setPriority(article.getPriority());
		articleDto.setUser(userService.convertToUserDto(article.getUser()));
		articleDto.setCategory(article.getCategory());

		return articleDto;
	}

	@Override
	public boolean createArticle(ArticleDto articleDto, String email) {
		Article article = convertToArticleEntity(articleDto);

		Optional<User> optionalUser = userRepository.findByEmail(email);

		if (optionalUser.isPresent()) {
			User user = optionalUser.get();

			article.setUser(user);
			articleRepository.save(article);
			return true;
		}
		return false;
	}

	@Override
	public boolean updateArticle(long id, ArticleStatus status, ArticlePriority priority, long categoryId, LocalDateTime date) {
		Optional<Article> optionalArticle = articleRepository.findById(id);

		if (optionalArticle.isPresent()) {
			Article article = optionalArticle.get();

			article.setStatus(status);
			article.setPriority(priority);
			Category category = categoryRepository.findById(categoryId).orElse(null);
	        article.setCategory(category);

			if (status == ArticleStatus.PUBLISHED && article.getPublicationDate() == null)
				article.setPublicationDate(date);
			if (status == ArticleStatus.PENDING)
				article.setPublicationDate(null);

			articleRepository.save(article);

			return articleRepository.existsById(article.getId());
		} else {
			return false;
		}
	}

	@Override
	public boolean deleteArticle(long id) {
		Optional<Article> optionalArticle = articleRepository.findById(id);

		if(optionalArticle.isPresent()) {
			Article article = optionalArticle.get();
			
			articleRepository.delete(article);
			return true;
		}
		return false;
	}
	
	@Override
	public Optional<ArticleDto> getLastHighlightedArticle() {
		Optional<Article> optionalArticle = articleRepository.findTopByStatusAndPriorityOrderByPublicationDateDesc(
				ArticleStatus.PUBLISHED, ArticlePriority.HIGHLIGHTED);

		return optionalArticle.map(this::convertToArticleDto);
	}

	@Override
	public List<ArticleDto> getRecentArticles(int count) {

		List<Article> recentArticles = articleRepository.findByStatusOrderByPublicationDateDesc(ArticleStatus.PUBLISHED,
				Limit.of(count));

		return recentArticles.stream().map(this::convertToArticleDto).collect(Collectors.toList());
	}

	@Override
	public List<ArticleDto> getFeaturedArticles(int count) {
		List<Article> featuredArticles = articleRepository.findByStatusAndPriorityOrderByPublicationDateDesc(
				ArticleStatus.PUBLISHED, ArticlePriority.FEATURED, Limit.of(count));

		return featuredArticles.stream().map(this::convertToArticleDto).collect(Collectors.toList());
	}

	@Override
    public List<ArticleDto> convertToArticleDtoList(List<Article> articles) {
        return articles.stream()
                .map(this::convertToArticleDto)
                .collect(Collectors.toList());
    }
	
    @Override
    public ArticleDto getArticleById(long id) {
        Optional<Article> optionalArticle = articleRepository.findById(id);
        return optionalArticle.map(this::convertToArticleDto).orElse(null);
    }
}