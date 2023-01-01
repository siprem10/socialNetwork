package com.ramidev.socialnetwork.mapper.profile;

import com.ramidev.socialnetwork.dto.profile.ProfileDto;
import com.ramidev.socialnetwork.entities.Profile;
import org.springframework.stereotype.Component;

@Component
public class ProfileMapper {

    //modificarlo para todos los casos
    public Profile toEntitiy(ProfileDto dto) {
        Profile profile = new Profile();
        profile.setId(dto.getId());
        profile.setProfilePic(dto.getProfilePic());
        profile.setCoverPhoto(dto.getCoverPhoto());
        profile.setDescription(dto.getDescription());
        profile.setCountry(dto.getCountry());
        profile.setCity(dto.getCity());
        profile.setHobbie(dto.getHobbie());
        profile.setJob(dto.getJob());
        profile.setUser(dto.getUser());
        return profile;
    }

    public ProfileDto toDtoFull(Profile profile) {
        ProfileDto dto = new ProfileDto();
        dto.setId(profile.getId());
        dto.setProfilePic(profile.getProfilePic());
        dto.setCoverPhoto(profile.getCoverPhoto());
        dto.setDescription(profile.getDescription());
        dto.setCountry(profile.getCountry());
        dto.setCity(profile.getCity());
        dto.setHobbie(profile.getHobbie());
        dto.setJob(profile.getJob());
        dto.setUser(profile.getUser());
        return dto;
    }

}
