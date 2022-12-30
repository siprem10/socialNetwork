package com.ramidev.socialnetwork.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class UserLoginDto {

    @NotEmpty(message = "El campo no puede estar vacio")
    @Email(message = "Email no válido")
    private String email;

    @NotEmpty(message = "El campo no puede estar vacio")
    private String password;
}
