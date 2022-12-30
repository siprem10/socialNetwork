package com.ramidev.socialnetwork.services;

import com.ramidev.socialnetwork.dto.*;

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
