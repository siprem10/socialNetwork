package com.ramidev.socialnetwork.dto.post;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime createdDate;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime updatedDate;
    private ProfileFromPostDto profile;
    private Set<Comment> comments = new HashSet<>();

}
