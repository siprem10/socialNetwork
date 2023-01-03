package com.ramidev.socialnetwork.controllers;

import com.ramidev.socialnetwork.dto.image.CloudinaryDto;
import com.ramidev.socialnetwork.dto.profile.ChangeImageDto;
import com.ramidev.socialnetwork.dto.profile.ProfileDto;
import com.ramidev.socialnetwork.dto.profile.ProfileEditDto;
import com.ramidev.socialnetwork.dto.profile.ProfileSimpleDto;
import com.ramidev.socialnetwork.entities.Profile;
import com.ramidev.socialnetwork.services.ProfileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/profile")
@CrossOrigin(origins = "*")
public class ProfileController {

    @Autowired
    private ProfileServiceImpl profileService;

    @GetMapping
    public ResponseEntity<Object> getAll() {
        List<ProfileDto> dto = profileService.getAllFull();
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/{email}")
    public ResponseEntity<Object> getByUserEmail(@PathVariable String email) {
        Profile profile = profileService.getByUserEmail(email);
        return ResponseEntity.ok(profile);
    }

    @GetMapping(value = "/simple")
    public ResponseEntity<Object> getAllSimple() {
        List<ProfileSimpleDto> dto = profileService.getAllSimple();
        return ResponseEntity.ok(dto);
    }

    @PatchMapping(value = "/{email}")
    public ResponseEntity<Object> edit(@PathVariable String email, @RequestBody Map<Object, Object> profileEdit) {
        ProfileEditDto dto = profileService.editByUserEmail(email, profileEdit);
        return ResponseEntity.ok(dto);
    }

    @PostMapping(value = "/changeImage")
    public ResponseEntity<Object> submitImage(@RequestParam MultipartFile image, @RequestParam ChangeImageDto type) {
        CloudinaryDto cloudinaryDto = profileService.submitImage(image, type);
        Map<Object, Object> imagesToEdit = new HashMap<>();

        if(type == ChangeImageDto.PIC) {
            imagesToEdit.put("profilePic", cloudinaryDto.getSecure_url());
        } else {
            imagesToEdit.put("coverPhoto", cloudinaryDto.getSecure_url());
        }
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        ProfileEditDto dto = profileService.editByUserEmail(email, imagesToEdit);

        return ResponseEntity.ok(dto);
    }
}
