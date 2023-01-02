package com.ramidev.socialnetwork.dto.profile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class ProfileEditDto {

    private Long id;
    private String profilePic;
    private String coverPhoto;
    private String description;
    private String country;
    private String city;
    private String hobbie;
    private String job;
}
