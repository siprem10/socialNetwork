package com.ramidev.socialnetwork.services;

import com.ramidev.socialnetwork.dto.comment.CommentCreateDto;
import com.ramidev.socialnetwork.dto.comment.CommentDto;
import com.ramidev.socialnetwork.dto.comment.CommentEditDto;

import java.util.List;

public interface CommentService {
    List<CommentDto> getAll();
    List<CommentDto> getAllByPostId(Long postId);
    CommentDto createInPost(Long postId, CommentCreateDto commentCreateDto);
    CommentDto editById(Long postId, Long id, CommentEditDto commentEditDto);
    String deleteById(Long postId, Long id);
}
