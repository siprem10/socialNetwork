package com.ramidev.socialnetwork.dto.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ramidev.socialnetwork.dto.timestamps.Timestamps;
import com.ramidev.socialnetwork.enums.Gender;
import com.ramidev.socialnetwork.enums.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter @Setter @ToString
public class UserDto extends Timestamps {

    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate birthdate;
    private Gender gender;

    private Role role = Role.getDefaultRole();
}
