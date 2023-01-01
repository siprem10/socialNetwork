package com.ramidev.socialnetwork.services;

import com.ramidev.socialnetwork.dto.profile.ProfileDto;
import com.ramidev.socialnetwork.entities.Profile;

import java.util.List;

public interface ProfileService {
    List<Profile> getAll();
    List<ProfileDto> getAllFull();
    Profile getByUserId(Long id);
}
