package com.ramidev.socialnetwork.services;

import com.ramidev.socialnetwork.dto.image.CloudinaryDto;
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
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

    // ver si lo agarro por id o id usuario
    @Override
    public Profile getByUserId(Long id) {
        return profileRepository.findByUserId(id)
                .orElseThrow(() -> new NotFoundException(String.format("Perfil con userId %s no encontrado!", id)));
    }

    @Override
    public List<ProfileSimpleDto> getAllSimple() {
        List<Profile> profile = profileRepository.findAll();
        return profile.stream().map(data -> profileSimpleMapper.toDto(data)).toList();
    }

    @Override
    public ProfileEditDto editById(Long id, ProfileEditDto profileEditDto) {
        Profile profile = profileRepository.findById(id).get();
        return profileEditMapper.toDto(profile);
    }

    public CloudinaryDto submitImage(MultipartFile image) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        String subfolder = "profile/"+email+"/";
        return cloudinaryService.submitImage(image, subfolder);
    }
}

