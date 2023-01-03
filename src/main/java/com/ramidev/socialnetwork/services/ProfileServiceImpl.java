package com.ramidev.socialnetwork.services;

import com.ramidev.socialnetwork.dto.image.CloudinaryDto;
import com.ramidev.socialnetwork.dto.profile.ChangeImageDto;
import com.ramidev.socialnetwork.dto.profile.ProfileDto;
import com.ramidev.socialnetwork.dto.profile.ProfileEditDto;
import com.ramidev.socialnetwork.dto.profile.ProfileSimpleDto;
import com.ramidev.socialnetwork.entities.Profile;
import com.ramidev.socialnetwork.exception.NotFoundException;
import com.ramidev.socialnetwork.dto.mapper.profile.ProfileEditMapper;
import com.ramidev.socialnetwork.dto.mapper.profile.ProfileMapper;
import com.ramidev.socialnetwork.dto.mapper.profile.ProfileSimpleMapper;
import com.ramidev.socialnetwork.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private ProfileMapper profileMapper;
    @Autowired
    private ProfileSimpleMapper profileSimpleMapper;

    @Autowired
    private ProfileEditMapper profileEditMapper;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Override
    public List<Profile> getAll() {
        return profileRepository.findAll();
    }

    @Override
    public List<ProfileDto> getAllFull() {
        List<Profile> profile = profileRepository.findAll();
        return profile.stream().map(data -> profileMapper.toDto(data)).toList();
    }

    @Override
    public Profile getByUserEmail(String email) {
        return profileRepository.findByUserEmail(email)
                .orElseThrow(() -> new NotFoundException(String.format("Perfil %s no encontrado!", email)));
    }

    @Override
    public List<ProfileSimpleDto> getAllSimple() {
        List<Profile> profile = profileRepository.findAll();
        return profile.stream().map(data -> profileSimpleMapper.toDto(data)).toList();
    }

    @Override
    public ProfileEditDto editByUserEmail(String email, Map<Object, Object> profileEdit) {
        Profile profile = profileRepository.findByUserEmail(email)
                .orElseThrow(() -> new NotFoundException(String.format("Perfil %s no encontrado!", email)));

        profileEdit.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Profile.class, (String) key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, profile, value);
        });
        return profileEditMapper.toDto(profileRepository.save(profile));
    }

    public CloudinaryDto submitImage(MultipartFile image, ChangeImageDto type) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        String subfolder = "profile/"+email+"/"+type;
        return cloudinaryService.submitImage(image, subfolder);
    }
}

