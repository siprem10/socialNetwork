package com.ramidev.socialnetwork.dto.post;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PostDto_profile_user {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
}
