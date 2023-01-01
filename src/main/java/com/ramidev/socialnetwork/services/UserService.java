package com.ramidev.socialnetwork.services;

import com.ramidev.socialnetwork.dto.user.*;
import com.ramidev.socialnetwork.security.dto.JwtDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAll();
    UserDto getByEmail(String id);
    UserDto register(UserRegisterDto userDto);

    JwtDto login(UserLoginDto userLoginDto);
    UserDto editByEmail(String email, UserEditDto userEditDto);
    String editPasswordByEmail(String email, UserEditPasswordDto userEditPasswordDto);

    String deleteByEmail(String email);
}
