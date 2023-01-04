package com.ramidev.socialnetwork.dto.post;

import com.ramidev.socialnetwork.entities.Profile;
import lombok.Data;
@Data
public class PostCreateDto {
    private String description;
    private String imageUrl;
    private Profile profile;
}
