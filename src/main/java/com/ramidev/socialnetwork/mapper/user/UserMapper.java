package com.ramidev.socialnetwork.mapper.user;

import com.ramidev.socialnetwork.dto.user.UserDto;
import com.ramidev.socialnetwork.dto.user.UserRegisterDto;
import com.ramidev.socialnetwork.entities.Profile;
import com.ramidev.socialnetwork.entities.User;
import com.ramidev.socialnetwork.utils.Password;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntitiy(UserDto dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setFirstname(dto.getFirstname());
        user.setLastname(dto.getLastname());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setBirthdate(dto.getBirthdate());
        user.setGender(dto.getGender());
        user.setCreatedDate(dto.getCreatedDate());
        user.setUpdatedDate(dto.getUpdatedDate());
        return user;
    }

    public User toEntitiySaved(UserRegisterDto dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setFirstname(dto.getFirstname());
        user.setLastname(dto.getLastname());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setBirthdate(dto.getBirthdate());
        user.setGender(dto.getGender());
        user.setCreatedDate(dto.getCreatedDate());
        user.setUpdatedDate(dto.getUpdatedDate());
        user.setPassword(Password.pwdEncoder(user.getPassword()));
        user.setProfile(new Profile(user));
        return user;
    }

    public UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setFirstname(user.getFirstname());
        dto.setLastname(user.getLastname());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        dto.setBirthdate(user.getBirthdate());
        dto.setGender(user.getGender());
        dto.setCreatedDate(user.getCreatedDate());
        dto.setUpdatedDate(user.getUpdatedDate());
        return dto;
    }
}
