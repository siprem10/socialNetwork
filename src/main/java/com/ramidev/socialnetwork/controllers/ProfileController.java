package com.ramidev.socialnetwork.controllers;

import com.ramidev.socialnetwork.dto.profile.ProfileChangeImgDto;
import com.ramidev.socialnetwork.dto.profile.ProfileDto;
import com.ramidev.socialnetwork.dto.profile.ProfileEditDto;
import com.ramidev.socialnetwork.dto.profile.ProfileSimpleDto;
import com.ramidev.socialnetwork.services.ProfileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/profile")
@CrossOrigin(origins = "*")
public class ProfileController {

    @Autowired
    private ProfileServiceImpl profileService;

    @GetMapping
    public ResponseEntity<List<ProfileDto>> getAll() {
        List<ProfileDto> dto = profileService.getAllFull();
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/{email}")
    public ResponseEntity<ProfileDto> getByUserEmail(@PathVariable String email) {
        ProfileDto dto = profileService.getByUserEmail(email);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/simple")
    public ResponseEntity<List<ProfileSimpleDto>> getAllSimple() {
        List<ProfileSimpleDto> dto = profileService.getAllSimple();
        return ResponseEntity.ok(dto);
    }

    @PatchMapping(value = "/{email}")
    public ResponseEntity<ProfileEditDto> edit(@PathVariable String email, @RequestBody Map<Object, Object> profileEdit) {
        ProfileEditDto dto = profileService.editByUserEmail(email, profileEdit);
        return ResponseEntity.ok(dto);
    }

    @PostMapping(value = "/changeImage/{email}")
    public ResponseEntity<ProfileEditDto> changeImage(@PathVariable String email, @RequestParam MultipartFile image, @RequestParam ProfileChangeImgDto type) {
        ProfileEditDto dto = profileService.changeImage(email, image, type);
        return ResponseEntity.ok(dto);
    }
}
