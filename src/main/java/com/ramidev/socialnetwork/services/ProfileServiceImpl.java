package com.ramidev.socialnetwork.services;

import com.ramidev.socialnetwork.dto.image.CloudinaryDto;
import com.ramidev.socialnetwork.dto.profile.ProfileChangeImgDto;
import com.ramidev.socialnetwork.dto.profile.ProfileDto;
import com.ramidev.socialnetwork.dto.profile.ProfileEditDto;
import com.ramidev.socialnetwork.dto.profile.ProfileSimpleDto;
import com.ramidev.socialnetwork.entities.Profile;
import com.ramidev.socialnetwork.exception.NotFoundException;
import com.ramidev.socialnetwork.mapper.profile.ProfileEditMapper;
import com.ramidev.socialnetwork.mapper.profile.ProfileMapper;
import com.ramidev.socialnetwork.mapper.profile.ProfileSimpleMapper;
import com.ramidev.socialnetwork.repositories.ProfileRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private ModelMapper mapper;

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
    public ProfileDto getByUserEmail(String email) {
        Profile profile = profileRepository.findByUserEmail(email)
                .orElseThrow(() -> new NotFoundException(String.format("Perfil %s no encontrado!", email)));

        return profileMapper.toDto(profile);
    }

    @Override
    public List<ProfileSimpleDto> getAllSimple() {
        List<Profile> profile = profileRepository.findAll();
        return profile.stream().map(data -> mapper.map(data, ProfileSimpleDto.class)).toList();
    }

    @Override
    public ProfileEditDto editByUserEmail(String email, Map<String, Object> profileEdit) {
        Profile profile = profileRepository.findByUserEmail(email)
                .orElseThrow(() -> new NotFoundException(String.format("Perfil %s no encontrado!", email)));

        profileEdit.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Profile.class, (String) key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, profile, value);
        });
        return profileEditMapper.toDto(profileRepository.save(profile));
    }

    @Override
    public ProfileEditDto changeImage(String email, MultipartFile image, ProfileChangeImgDto type) {
        CloudinaryDto cloudinaryDto = submitImage(image, type);
        Map<String, Object> fieldToEdit = calculateFieldToEdit(type, cloudinaryDto.getSecure_url());

        return editByUserEmail(email, fieldToEdit);
    }

    private CloudinaryDto submitImage(MultipartFile image, ProfileChangeImgDto type) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        String subfolder = "profile/"+email+"/"+type;
        return cloudinaryService.submitImage(image, subfolder);
    }

    private Map<String, Object> calculateFieldToEdit(ProfileChangeImgDto type, String url){
        Map<String, Object> imagesToEdit = new HashMap<>();
        if(ProfileChangeImgDto.PIC.equals(type)) {
            imagesToEdit.put("profilePic", url);
        } else {
            imagesToEdit.put("coverPhoto", url);
        }
        return imagesToEdit;
    }
}

