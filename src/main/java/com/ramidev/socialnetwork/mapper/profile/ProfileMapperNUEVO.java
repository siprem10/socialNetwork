package com.ramidev.socialnetwork.mapper.profile;

import com.ramidev.socialnetwork.dto.profile.ProfileSimpleDto;
import com.ramidev.socialnetwork.entities.Profile;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProfileMapperNUEVO {

    @Autowired
    private ModelMapper mapper;

    public ProfileSimpleDto entityToProfileSimple(Profile data) {
        return mapper.map(data, ProfileSimpleDto.class);
    }

}
