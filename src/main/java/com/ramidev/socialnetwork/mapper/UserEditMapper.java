package com.ramidev.socialnetwork.mapper;

import com.ramidev.socialnetwork.dto.UserEditDto;
import com.ramidev.socialnetwork.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserEditMapper {

    public User toEntity(UserEditDto userEditDto){
        User user = new User();
        user.setId(userEditDto.getId());
        user.setFirstname(userEditDto.getFirstname());
        user.setLastname(userEditDto.getLastname());
        user.setBirthdate(userEditDto.getBirthdate());
        user.setGender(userEditDto.getGender());
        return user;
    }

    public UserEditDto toDto(User user){
        UserEditDto userEditDto = new UserEditDto();
        userEditDto.setId(user.getId());
        userEditDto.setFirstname(user.getFirstname());
        userEditDto.setLastname(user.getLastname());
        userEditDto.setBirthdate(user.getBirthdate());
        userEditDto.setGender(user.getGender());
        return userEditDto;
    }
}
