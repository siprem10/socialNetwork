package com.ramidev.socialnetwork.services;

import com.ramidev.socialnetwork.dto.user.UserDto;
import com.ramidev.socialnetwork.dto.user.UserEditDto;
import com.ramidev.socialnetwork.dto.user.UserEditPasswordDto;
import com.ramidev.socialnetwork.dto.user.UserLoginDto;
import com.ramidev.socialnetwork.entities.User;
import com.ramidev.socialnetwork.exception.ForbiddenException;
import com.ramidev.socialnetwork.exception.NotFoundException;
import com.ramidev.socialnetwork.exception.UniqueException;
import com.ramidev.socialnetwork.mapper.UserEditMapper;
import com.ramidev.socialnetwork.mapper.UserMapper;
import com.ramidev.socialnetwork.repositories.UserRepository;
import com.ramidev.socialnetwork.security.dto.JwtDto;
import com.ramidev.socialnetwork.security.jwt.JwtProvider;
import com.ramidev.socialnetwork.utils.Password;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
                .stream().map(user -> userMapper.toDto(user)).collect(Collectors.toList());
    }

    @Override
    public UserDto getByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException(String.format("El usuario %s no existe!", email)));
        return userMapper.toDto(user);
    }

    @Override
    public UserDto register(UserDto userDto) {
        if(userRepository.existsUserByEmail(userDto.getEmail()))
            throw new UniqueException("El usuario ya está registrado");

        try {
            User user = userMapper.toEntitiy(userDto);
            user.setPassword(Password.pwdEncoder(user.getPassword()));
            return userMapper.toDto(userRepository.save(user));
        } catch (Exception e) {
            throw new NotFoundException(e.getMessage());
        }
    }

    @Override
    public JwtDto login(UserLoginDto userLoginDto) {
        if(!userRepository.existsUserByEmail(userLoginDto.getEmail()))
            throw new NotFoundException("El usuario no está registrado!");

        // lo agarra el commence sinó
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginDto.getEmail(), userLoginDto.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtProvider.generateTokenFromEmail(authentication.getName());
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());

        } catch (Exception e){
            throw new ForbiddenException("Credenciales inválidas!");
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
    public UserDto editPasswordByEmail(String email, UserEditPasswordDto userEditPasswordDto) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException(String.format("El usuario %s no existe!", email)));

        if(!Password.pwdCompare(userEditPasswordDto.getPassword(), user.getPassword()))
            throw new ForbiddenException("Contraseña antigua incorrecta!");

        if(userEditPasswordDto.getPassword().equals(userEditPasswordDto.getNewPassword()))
            throw new ForbiddenException("La nueva contraseña no puede ser la misma!");

        user.setPassword(Password.pwdEncoder(userEditPasswordDto.getNewPassword()));

        return userMapper.toDto(userRepository.save(user));
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
//        if(!Password.pwdCompare(userEditPasswordDto.getPassword(), user.getPassword()))
//            throw new ForbiddenException("Contraseña antigua incorrecta!");
//
//        if(userEditPasswordDto.getPassword().equals(userEditPasswordDto.getNewPassword()))
//            throw new ForbiddenException("La nueva contraseña no puede ser la misma!");
//
//        user.setPassword(Password.pwdEncoder(userEditPasswordDto.getNewPassword()));
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
