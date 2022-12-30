package com.ramidev.socialnetwork.dto.user;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class UserEditPasswordDto {

    @NotEmpty(message = "El campo no puede estar vacio")
    private String password;

    @NotEmpty(message = "El campo no puede estar vacio")
    @Size(min = 8, message = "La nueva contraseña es muy corta")
    private String newPassword;
}
