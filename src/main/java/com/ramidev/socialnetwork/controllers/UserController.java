package com.ramidev.socialnetwork.controllers;

import com.ramidev.socialnetwork.dto.user.*;
import com.ramidev.socialnetwork.security.dto.JwtDto;
import com.ramidev.socialnetwork.services.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping
    public ResponseEntity< List<UserDto> > getAll() {
        List<UserDto> users = userService.getAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping(value = "/{email}")
    public ResponseEntity <UserDto> getByEmail(@PathVariable String email) {
        UserDto user = userService.getByEmail(email);
        return ResponseEntity.ok(user);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<UserDto> register(@Valid @RequestBody UserRegisterDto userDto) {
        UserDto user = userService.register(userDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody UserLoginDto userLoginDto) {
        JwtDto jwtDto = userService.login(userLoginDto);
        return ResponseEntity.ok(jwtDto);
    }

    @PutMapping(value = "/{email}")
    public ResponseEntity<UserDto> editByEmail(@PathVariable String email, @Valid @RequestBody UserEditDto userEditDto) {
        UserDto user = userService.editByEmail(email, userEditDto);
        return ResponseEntity.ok(user);
    }

    @PutMapping(value = "/change-password/{email}")
    public ResponseEntity<String> changePassword(@PathVariable String email, @Valid @RequestBody UserEditPasswordDto userEditPasswordDto) {
        String result = userService.editPasswordByEmail(email, userEditPasswordDto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping(value = "/{email}")
    public ResponseEntity<String> deleteByEmail(@PathVariable String email) {
        String result = userService.deleteByEmail(email);
        return ResponseEntity.ok(result);
    }
}
