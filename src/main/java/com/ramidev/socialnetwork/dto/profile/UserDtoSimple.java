package com.ramidev.socialnetwork.dto.profile;

import com.ramidev.socialnetwork.dto.timestamps.Timestamps;
import com.ramidev.socialnetwork.enums.Gender;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class UserDtoSimple extends Timestamps {
    private Long id;
    private String firstname;
    private String lastname;
    private Gender gender;
}

