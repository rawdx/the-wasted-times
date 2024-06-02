package com.newspaper.services;

import com.newspaper.entities.Comment;
import com.newspaper.entities.dtos.CommentDto;

/**
 * Service interface for comment operations.
 */

public interface CommentService {

    /**
     * Converts a CommentDto to a Comment entity.
     *
     * @param commentDto The CommentDto to be converted.
     * @return The corresponding Comment entity.
     */
    Comment convertToCommentEntity(CommentDto commentDto);

    /**
     * Converts a Comment entity to a CommentDto.
     *
     * @param comment The Comment entity to be converted.
     * @return The corresponding CommentDto.
     */
    CommentDto convertToCommentDto(Comment comment);

    /**
     * Creates a new comment based on the provided CommentDto, associated with a specific article and user email.
     *
     * @param commentDto The CommentDto containing comment details for creation.
     * @param articleId The ID of the article to which the comment is associated.
     * @param email The email of the user creating the comment.
     * @return True if the comment creation is successful, false otherwise.
     */
    boolean createComment(CommentDto commentDto, long articleId, String email);
}
