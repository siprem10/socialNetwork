package com.ramidev.socialnetwork.mapper;

import com.ramidev.socialnetwork.dto.user.UserEditDto;
import com.ramidev.socialnetwork.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserEditMapper {

    public User toEntity(UserEditDto dto){
        User user = new User();
        user.setId(dto.getId());
        user.setFirstname(dto.getFirstname());
        user.setLastname(dto.getLastname());
        user.setBirthdate(dto.getBirthdate());
        user.setGender(dto.getGender());
        user.setCreatedDate(dto.getCreatedDate());
        user.setUpdatedDate(dto.getUpdatedDate());
        return user;
    }

    public UserEditDto toDto(User user){
        UserEditDto userEditDto = new UserEditDto();
        userEditDto.setId(user.getId());
        userEditDto.setFirstname(user.getFirstname());
        userEditDto.setLastname(user.getLastname());
        userEditDto.setBirthdate(user.getBirthdate());
        userEditDto.setGender(user.getGender());
        userEditDto.setCreatedDate(user.getCreatedDate());
        userEditDto.setUpdatedDate(user.getUpdatedDate());
        return userEditDto;
    }
}
