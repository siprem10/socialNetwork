package com.ramidev.socialnetwork.controllers;

import com.ramidev.socialnetwork.dto.image.CloudinaryDto;
import com.ramidev.socialnetwork.dto.profile.ProfileDto;
import com.ramidev.socialnetwork.dto.profile.ProfileEditDto;
import com.ramidev.socialnetwork.dto.profile.ProfileSimpleDto;
import com.ramidev.socialnetwork.entities.Profile;
import com.ramidev.socialnetwork.services.CloudinaryService;
import com.ramidev.socialnetwork.services.ProfileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/profile")
@CrossOrigin(origins = "*")
public class ProfileController {

    @Autowired
    private ProfileServiceImpl profileService;

    @Autowired
    private CloudinaryService cloudinaryService;

    @GetMapping
    public ResponseEntity<Object> getAll() {
        List<ProfileDto> dto = profileService.getAllFull();
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getByUserId(@PathVariable Long id) {
        Profile profile = profileService.getByUserId(id);
        return ResponseEntity.ok(profile);
    }

    @GetMapping(value = "/simple")
    public ResponseEntity<Object> getAllSimple() {
        List<ProfileSimpleDto> dto = profileService.getAllSimple();
        return ResponseEntity.ok(dto);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Object> edit(@PathVariable Long id, ProfileEditDto profileEditDto) {
        ProfileEditDto dto = profileService.editById(id, profileEditDto);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<Object> subitImage(@RequestParam MultipartFile image) throws IOException {
        CloudinaryDto response = cloudinaryService.submitImage(image);
        return ResponseEntity.ok(response);
    }
}
