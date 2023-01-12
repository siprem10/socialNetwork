package com.ramidev.socialnetwork.dto.comment;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class CommentPostDto {
    private Long id;
    private String description;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
