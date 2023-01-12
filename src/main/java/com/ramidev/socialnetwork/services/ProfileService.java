package com.ramidev.socialnetwork.services;

import com.ramidev.socialnetwork.dto.profile.ChangeImageDto;
import com.ramidev.socialnetwork.dto.profile.ProfileDto;
import com.ramidev.socialnetwork.dto.profile.ProfileEditDto;
import com.ramidev.socialnetwork.dto.profile.ProfileSimpleDto;
import com.ramidev.socialnetwork.entities.Profile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface ProfileService {
    List<Profile> getAll();
    List<ProfileDto> getAllFull();
    List<ProfileSimpleDto> getAllSimple();
    ProfileDto getByUserEmail(String email);
    ProfileEditDto editByUserEmail(String email, Map<Object, Object> profileEdit);
    ProfileEditDto changeImage(String email, MultipartFile image, ChangeImageDto type);
}
