package com.ramidev.socialnetwork.services;

import com.ramidev.socialnetwork.dto.user.UserDto;
import com.ramidev.socialnetwork.dto.user.UserEditDto;
import com.ramidev.socialnetwork.dto.user.UserEditPasswordDto;
import com.ramidev.socialnetwork.dto.user.UserLoginDto;
import com.ramidev.socialnetwork.security.dto.JwtDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAll();
    UserDto getByEmail(String id);
    UserDto register(UserDto userDto);

    JwtDto login(UserLoginDto userLoginDto);
    UserDto editByEmail(String email, UserEditDto userEditDto);
    UserDto editPasswordByEmail(String email, UserEditPasswordDto userEditPasswordDto);

    String deleteByEmail(String email);
}
