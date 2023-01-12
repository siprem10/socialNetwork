package com.ramidev.socialnetwork.dto.post;

import lombok.Data;

@Data
public class PostDto_profile {
    private String profilePic;
    private PostDto_profile_user user;
}
