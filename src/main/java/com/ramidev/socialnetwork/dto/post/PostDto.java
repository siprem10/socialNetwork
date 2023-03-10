package com.ramidev.socialnetwork.dto.post;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ramidev.socialnetwork.dto.comment.CommentDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
public class PostDto {
    private Long id;
    private String description;
    private String imageUrl;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime createdDate;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime updatedDate;
    private PostDto_profile profile;
    private Set<CommentDto> comments = new HashSet<>();
}
