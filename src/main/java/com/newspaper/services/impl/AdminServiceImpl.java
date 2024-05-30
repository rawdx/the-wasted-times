package com.newspaper.services.impl;

import com.newspaper.entities.Article;
import com.newspaper.entities.dtos.ArticleDto;
import com.newspaper.repositories.ArticleRepository;
import com.newspaper.services.ArticleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.newspaper.entities.User;
import com.newspaper.entities.dtos.UserDto;
import com.newspaper.repositories.UserRepository;
import com.newspaper.services.AdminService;
import com.newspaper.services.UserService;


/**
 * Implementation of the {@link AdminService} interface for admin-related operations.
 */
@Service
public class AdminServiceImpl implements AdminService {

	private final UserService userService;
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;
    private final ArticleService articleService;

    public AdminServiceImpl(UserService userService, UserRepository userRepository, ArticleRepository articleRepository, ArticleService articleService) {
    	this.userService = userService;
        this.userRepository = userRepository;
        this.articleRepository = articleRepository;
        this.articleService = articleService;
    }
    
    @Override
    public Page<UserDto> getUserDtosPage(Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("id"));

        Page<User> userPage = userRepository.findAll(pageable);
        return userPage.map(userService::convertToUserDto);
    }

    @Override
    public Page<ArticleDto> getArticleDtosPage(Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("id"));

        Page<Article> articlePage = articleRepository.findAllWithUser(pageable);
        return articlePage.map(articleService::convertToArticleDto);
    }

    @Override
    public Page<UserDto> searchUsers(String query, Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("id"));

        Page<User> userPage = userRepository.searchByEmailOrName(query, pageable);
        return userPage.map(userService::convertToUserDto);
    }
}
