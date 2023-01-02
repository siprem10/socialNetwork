package com.ramidev.socialnetwork.services;

import com.ramidev.socialnetwork.dto.profile.ProfileDto;
import com.ramidev.socialnetwork.dto.profile.ProfileSimpleDto;
import com.ramidev.socialnetwork.entities.Profile;

import java.util.List;

public interface ProfileService {
    List<Profile> getAll();
    List<ProfileDto> getAllFull();
    List<ProfileSimpleDto> getAllSimple();
    Profile getByUserId(Long id);
}
