package com.ramidev.socialnetwork.services;

import com.ramidev.socialnetwork.dto.profile.ProfileDto;
import com.ramidev.socialnetwork.dto.profile.ProfileEditDto;
import com.ramidev.socialnetwork.dto.profile.ProfileSimpleDto;
import com.ramidev.socialnetwork.entities.Profile;

import java.util.List;
import java.util.Map;

public interface ProfileService {
    List<Profile> getAll();
    List<ProfileDto> getAllFull();
    List<ProfileSimpleDto> getAllSimple();
    Profile getByUserEmail(String email);
    ProfileEditDto editByUserEmail(String email, Map<Object, Object> profileEdit);
}
