package com.ramidev.socialnetwork.mapper.profile;

import com.ramidev.socialnetwork.dto.profile.ProfileEditDto;
import com.ramidev.socialnetwork.entities.Profile;
import org.springframework.stereotype.Component;

@Component
public class ProfileEditMapper {
    public Profile toEntity(ProfileEditDto dto) {
        Profile profile = new Profile();
        profile.setId(dto.getId());
        profile.setProfilePic(dto.getProfilePic());
        profile.setCoverPhoto(dto.getCoverPhoto());
        profile.setDescription(dto.getDescription());
        profile.setCountry(dto.getCountry());
        profile.setCity(dto.getCity());
        profile.setHobbie(dto.getHobbie());
        profile.setJob(dto.getJob());
        return profile;
    }

    public ProfileEditDto toDto(Profile profile) {
        ProfileEditDto dto = new ProfileEditDto();
        dto.setId(profile.getId());
        dto.setProfilePic(profile.getProfilePic());
        dto.setCoverPhoto(profile.getCoverPhoto());
        dto.setDescription(profile.getDescription());
        dto.setCountry(profile.getCountry());
        dto.setCity(profile.getCity());
        dto.setHobbie(profile.getHobbie());
        dto.setJob(profile.getJob());
        return dto;
    }

}
