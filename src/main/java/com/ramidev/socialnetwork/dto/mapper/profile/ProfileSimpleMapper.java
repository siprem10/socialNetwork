package com.ramidev.socialnetwork.dto.mapper.profile;

import com.ramidev.socialnetwork.dto.profile.ProfileSimpleDto;
import com.ramidev.socialnetwork.entities.Profile;
import org.springframework.stereotype.Component;

@Component
public class ProfileSimpleMapper {

    public Profile toEntity(ProfileSimpleDto dto) {
        Profile profile = new Profile();
        profile.setId(dto.getId());
        profile.setProfilePic(dto.getProfilePic());
        profile.setCity(dto.getCity());
        profile.setHobbie(dto.getHobbie());
        profile.setJob(dto.getJob());
        profile.setUser(dto.getUser());
        return profile;
    }

    public ProfileSimpleDto toDto(Profile profile) {
        ProfileSimpleDto dto = new ProfileSimpleDto();
        dto.setId(profile.getId());
        dto.setProfilePic(profile.getProfilePic());
        dto.setCity(profile.getCity());
        dto.setHobbie(profile.getHobbie());
        dto.setJob(profile.getJob());
        dto.setUser(profile.getUser());
        return dto;
    }

}
