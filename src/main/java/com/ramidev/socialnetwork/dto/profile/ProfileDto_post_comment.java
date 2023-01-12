package com.ramidev.socialnetwork.dto.profile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ProfileDto_post_comment {
    private Long id;
    private String description;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
