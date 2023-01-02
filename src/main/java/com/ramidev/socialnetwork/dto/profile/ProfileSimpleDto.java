package com.ramidev.socialnetwork.dto.profile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ramidev.socialnetwork.entities.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class ProfileSimpleDto {

    private Long id;
    private String profilePic;
    private String city;
    private String hobbie;
    private String job;
    @JsonIgnoreProperties({"password", "email", "role", "birthdate"})
    private User user;
}
