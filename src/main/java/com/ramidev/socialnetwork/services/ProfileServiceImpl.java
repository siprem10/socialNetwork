package com.ramidev.socialnetwork.services;

import com.ramidev.socialnetwork.dto.profile.ProfileDto;
import com.ramidev.socialnetwork.entities.Profile;
import com.ramidev.socialnetwork.exception.NotFoundException;
import com.ramidev.socialnetwork.mapper.profile.ProfileMapper;
import com.ramidev.socialnetwork.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private ProfileMapper profileMapper;

    @Override
    public List<Profile> getAll() {
        return profileRepository.findAll();
    }

    @Override
    public List<ProfileDto> getAllFull() {
        List<Profile> profile = profileRepository.findAll();
        return profile.stream().map(data -> profileMapper.toDtoFull(data)).toList();
    }

    @Override
    public Profile getByUserId(Long id) {
        return profileRepository.findByUserId(id)
                .orElseThrow(() -> new NotFoundException(String.format("Perfil con userId %s no encontrado!", id)));
    }
}
