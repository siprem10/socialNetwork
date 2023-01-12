package com.ramidev.socialnetwork.dto.comment;

import com.ramidev.socialnetwork.dto.post.PostDto_profile_user;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommentDto_profile {
    private String profilePic;
    private PostDto_profile_user user;
}
