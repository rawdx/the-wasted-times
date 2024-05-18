package com.newspaper.services;

import com.newspaper.entities.Comment;
import com.newspaper.entities.dtos.CommentDto;

import java.util.List;

public interface CommentService {
    Comment convertToCommentEntity(CommentDto commentDto);

    CommentDto convertToCommentDto(Comment comment);

    boolean createComment(CommentDto commentDto, long articleId, String email);

    List<CommentDto> getAllComments();
}
