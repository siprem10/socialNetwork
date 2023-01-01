package com.ramidev.socialnetwork.controllers;

import com.ramidev.socialnetwork.dto.profile.ProfileDto;
import com.ramidev.socialnetwork.entities.Profile;
import com.ramidev.socialnetwork.services.ProfileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping(value = "/{idUser}")
    public ResponseEntity<Object> getByUserId(@RequestParam Long id) {
        Profile profile = profileService.getByUserId(id);
        return ResponseEntity.ok(profile);
    }
}
