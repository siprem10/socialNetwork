package com.ramidev.socialnetwork.dto.profile;

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
    private UserDtoSimple user;
}
