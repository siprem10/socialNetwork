package com.ramidev.socialnetwork.services;

import com.ramidev.socialnetwork.dto.post.PostCreateDto;
import com.ramidev.socialnetwork.dto.post.PostDto;

import java.util.List;
import java.util.Map;

public interface PostService {
    List<PostDto> getAll();
    PostDto getById(Long id);
    PostDto create(String email, PostCreateDto postCreateDto);
    PostDto editById(Long id, Map<Object, Object> profileEdit);
    String deleteById(Long id);
}
