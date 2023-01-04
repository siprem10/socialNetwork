package com.ramidev.socialnetwork.dto.post;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.ramidev.socialnetwork.entities.User;
import lombok.Data;

@Data
public class ProfileFromPostDto {
    private String profilePic;
    @JsonIncludeProperties({"firstname", "lastname", "email"})
    private User user;
}
