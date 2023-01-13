package com.ramidev.socialnetwork.dto.comment;

import com.ramidev.socialnetwork.dto.post.PostDto_profile;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter @Setter @ToString
public class CommentDto {
    private Long id;
    private String description;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private PostDto_profile profile;
}
