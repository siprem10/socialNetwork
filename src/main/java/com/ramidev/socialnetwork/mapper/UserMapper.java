package com.ramidev.socialnetwork.mapper;

import com.ramidev.socialnetwork.dto.UserDto;
import com.ramidev.socialnetwork.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntitiy(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setFirstname(userDto.getFirstname());
        user.setLastname(userDto.getLastname());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setBirthdate(userDto.getBirthdate());
        user.setGender(userDto.getGender());
        return user;
    }

    public UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstname(user.getFirstname());
        userDto.setLastname(user.getLastname());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setBirthdate(user.getBirthdate());
        userDto.setGender(user.getGender());
        return userDto;
    }
}
