package com.ramidev.socialnetwork.mapper.profile;

import com.ramidev.socialnetwork.dto.profile.ProfileDto;
import com.ramidev.socialnetwork.dto.profile.ProfileEditDto;
import com.ramidev.socialnetwork.dto.profile.ProfileSimpleDto;
import com.ramidev.socialnetwork.entities.Profile;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProfileMapper {

    private final ModelMapper mapper;

    public ProfileMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public ProfileDto entityToProfileDto(Profile data) {
        return mapper.map(data, ProfileDto.class);
    }

    public ProfileEditDto entityToProfileEditDto(Profile data) {
        return mapper.map(data, ProfileEditDto.class);
    }

    public ProfileSimpleDto entityToProfileSimpleDto(Profile data) {
        return mapper.map(data, ProfileSimpleDto.class);
    }

}
