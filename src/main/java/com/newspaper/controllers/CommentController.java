package com.newspaper.controllers;

import com.newspaper.entities.dtos.CommentDto;
import com.newspaper.entities.dtos.UserDto;
import com.newspaper.services.CommentService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
public class CommentController {

    private static final Logger logger = LoggerFactory.getLogger(CommentController.class);

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/comment/create")
    public String createComment(HttpSession session, @RequestParam String content, @RequestParam long articleId) {
        try {
            UserDto user = (UserDto) session.getAttribute("user");

            CommentDto comment = new CommentDto(content, LocalDateTime.now());

            if (commentService.createComment(comment, articleId, user.getEmail())) {
                logger.info("Comment created successfully by user {}", user.getEmail());
            } else{
                logger.warn("Failed to create comment by user {}", user.getEmail());
            }
            return "redirect:/article/" + articleId;
        } catch (Exception e) {
            logger.error("Error creating comment", e);
            return "redirect:/article/" + articleId;
        }
    }
}
