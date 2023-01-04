package com.ramidev.socialnetwork.dto.post;

import com.ramidev.socialnetwork.entities.Comment;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
public class PostDto {
    private Long id;
    private String description;
    private String imageUrl;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private ProfileFromPostDto profile;
    private Set<Comment> comments = new HashSet<>();
}
