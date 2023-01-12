package com.ramidev.socialnetwork.services;

import com.ramidev.socialnetwork.dto.comment.CommentCreateDto;
import com.ramidev.socialnetwork.dto.comment.CommentDto;
import com.ramidev.socialnetwork.dto.comment.CommentEditDto;
import com.ramidev.socialnetwork.dto.comment.CommentPostDto;

import java.util.List;

public interface CommentService {
    List<CommentDto> getAll();
    List<CommentDto> getAllByPostId(Long postId);
    CommentPostDto createInPost(Long postId, String email, CommentCreateDto commentCreateDto);
    CommentDto editById(Long postId, Long id, CommentEditDto commentEditDto);
    String deleteById(Long postId, Long id);
}
