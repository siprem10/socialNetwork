package com.ramidev.socialnetwork.services;

import com.ramidev.socialnetwork.dto.user.*;
import com.ramidev.socialnetwork.entities.User;
import com.ramidev.socialnetwork.exception.ForbiddenException;
import com.ramidev.socialnetwork.exception.NotFoundException;
import com.ramidev.socialnetwork.exception.UniqueException;
import com.ramidev.socialnetwork.mapper.user.UserEditMapper;
import com.ramidev.socialnetwork.mapper.user.UserMapper;
import com.ramidev.socialnetwork.repositories.UserRepository;
import com.ramidev.socialnetwork.security.dto.JwtDto;
import com.ramidev.socialnetwork.security.jwt.JwtProvider;
import com.ramidev.socialnetwork.utils.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserEditMapper mapperEdt;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public List<UserDto> getAll() {

        return userRepository.findAll()
                .stream().map(user -> userMapper.toDto(user)).toList();
    }

    @Override
    public UserDto getByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException(String.format("El usuario %s no existe!", email)));
        return userMapper.toDto(user);
    }

    @Override
    public UserDto register(UserRegisterDto userDto) {
        if(userRepository.existsUserByEmail(userDto.getEmail()))
            throw new UniqueException("El usuario ya est?? registrado");

        try {
            User user = userMapper.toEntitiySaved(userDto);
            return userMapper.toDto(userRepository.save(user));
        } catch (Exception e) {
            throw new NotFoundException(e.getMessage());
        }
    }

//    @Override
//    public UserDto register(UserDto userDto) {
//        if(userRepository.existsUserByEmail(userDto.getEmail()))
//            throw new UniqueException("El usuario ya est?? registrado");
//
//        try {
//            User user = userMapper.toEntitiy(userDto);
//            user.setPassword(PasswordUtil.pwdEncoder(user.getPassword()));
//            Profile profile = new Profile(user);
//            user.setProfile(profile);
//
//            return userMapper.toDto(userRepository.save(user));
//        } catch (Exception e) {
//            throw new NotFoundException(e.getMessage());
//        }
//    }

    @Override
    public JwtDto login(UserLoginDto userLoginDto) {
        if(!userRepository.existsUserByEmail(userLoginDto.getEmail()))
            throw new NotFoundException("El usuario no est?? registrado!");

        // lo agarra el commence sin??
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginDto.getEmail(), userLoginDto.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtProvider.generateTokenFromEmail(authentication.getName());
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());

        } catch (Exception e){
            throw new ForbiddenException("Credenciales inv??lidas!");
        }
    }

    @Override
    public UserDto editByEmail(String email, UserEditDto userEditDto) {
        User user = userRepository.findByEmail(email).map(data -> {
                data.setFirstname(userEditDto.getFirstname());
                data.setLastname(userEditDto.getLastname());
                data.setBirthdate(userEditDto.getBirthdate());
                data.setGender(userEditDto.getGender());
                return data;
            })
            .orElseThrow(() -> new NotFoundException(String.format("El usuario %s no existe!", email)));

        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public String editPasswordByEmail(String email, UserEditPasswordDto userEditPasswordDto) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException(String.format("El usuario %s no existe!", email)));

        if(!PasswordUtil.pwdCompare(userEditPasswordDto.getPassword(), user.getPassword()))
            throw new ForbiddenException("Contrase??a antigua incorrecta!");

        if(userEditPasswordDto.getPassword().equals(userEditPasswordDto.getNewPassword()))
            throw new ForbiddenException("La nueva contrase??a no puede ser la misma!");

        user.setPassword(PasswordUtil.pwdEncoder(userEditPasswordDto.getNewPassword()));
        userRepository.save(user);

        return "Contrase??a modificada correctamente!";
    }

//    @Override
//    public UserDto editByEmail(String email, UserEditDto userEditDto) {
//        User user = userMapper.toEntitiy(getByEmail(email));
//        user.setFirstname(userEditDto.getFirstname());
//        user.setLastname(userEditDto.getLastname());
//        user.setBirthdate(userEditDto.getBirthdate());
//        user.setGender(userEditDto.getGender());
//
//        return userMapper.toDto(userRepository.save(user));
//    }

//    @Override
//    public UserDto editPasswordByEmail(String email, UserEditPasswordDto userEditPasswordDto) {
//        User user = userMapper.toEntitiy(getByEmail(email));
//
//        if(!PasswordUtil.pwdCompare(userEditPasswordDto.getPassword(), user.getPassword()))
//            throw new ForbiddenException("Contrase??a antigua incorrecta!");
//
//        if(userEditPasswordDto.getPassword().equals(userEditPasswordDto.getNewPassword()))
//            throw new ForbiddenException("La nueva contrase??a no puede ser la misma!");
//
//        user.setPassword(PasswordUtil.pwdEncoder(userEditPasswordDto.getNewPassword()));
//
//        return userMapper.toDto(userRepository.save(user));
//    }

    @Override
    public String deleteByEmail(String email) {
        if(!userRepository.existsUserByEmail(email))
            throw new NotFoundException(String.format("El usuario %s no existe!", email));

        userRepository.deleteByEmail(email);

        return "Usuario eliminado correctamente!";
    }
}
