package com.ramidev.socialnetwork.dto.comment;

import com.ramidev.socialnetwork.entities.Post;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDto {
    private Long id;
    private String description;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private Post post; //x
}
