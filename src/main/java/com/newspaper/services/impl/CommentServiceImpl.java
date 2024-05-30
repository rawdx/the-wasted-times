package com.newspaper.services.impl;

import com.newspaper.entities.Article;
import com.newspaper.entities.Comment;
import com.newspaper.entities.User;
import com.newspaper.entities.dtos.CommentDto;
import com.newspaper.repositories.ArticleRepository;
import com.newspaper.repositories.CommentRepository;
import com.newspaper.repositories.UserRepository;
import com.newspaper.services.ArticleService;
import com.newspaper.services.CommentService;
import com.newspaper.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    public CommentServiceImpl(CommentRepository commentRepository, ArticleRepository articleRepository, UserRepository userRepository, UserService userService) {
        this.commentRepository = commentRepository;
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Override
    public Comment convertToCommentEntity(CommentDto commentDto) {
        Comment comment = new Comment();

        comment.setContent(commentDto.getContent());
        comment.setCreationDate(commentDto.getCreationDate());

        return comment;
    }

    @Override
    public CommentDto convertToCommentDto(Comment comment) {
        CommentDto commentDto = new CommentDto();

        commentDto.setId(comment.getId());
        commentDto.setContent(comment.getContent());
        commentDto.setCreationDate(comment.getCreationDate());
        commentDto.setUser(userService.convertToUserDto(comment.getUser()));

        return commentDto;
    }

    @Override
    public boolean createComment(CommentDto commentDto, long articleId, String email) {
        Comment comment = convertToCommentEntity(commentDto);

        Optional<Article> optionalArticle = articleRepository.findById(articleId);
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalArticle.isPresent() && optionalUser.isPresent()) {
            Article article = optionalArticle.get();
            User user = optionalUser.get();

            comment.setArticle(article);
            comment.setUser(user);

            commentRepository.save(comment);
            return true;
        }
        return false;
    }
}
