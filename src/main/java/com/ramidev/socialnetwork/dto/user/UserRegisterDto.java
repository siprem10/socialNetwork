package com.ramidev.socialnetwork.dto.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ramidev.socialnetwork.dto.timestamps.Timestamps;
import com.ramidev.socialnetwork.enums.Gender;
import com.ramidev.socialnetwork.enums.Role;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter @Setter @ToString
public class UserRegisterDto extends Timestamps {

    private Long id;

    @NotBlank(message = "El campo no puede estar vacio")
    @Size(min = 3, message = "El campo debe contener al menos 3 caracteres")
    @Size(max = 20, message = "El campo no puede superar los 20 caracteres")
    private String firstname;

    @NotBlank(message = "El campo no puede estar vacio")
    @Size(min = 3, message = "El campo debe contener al menos 3 caracteres")
    @Size(max = 20, message = "El campo no puede superar los 20 caracteres")
    private String lastname;

    @NotEmpty(message = "El campo no puede estar vacio")
    @Email(message = "Email no válido")
    private String email;

    @Size(min = 8, message = "La contraseña es muy corta")
    private String password;

    @NotNull(message = "El campo no puede estar vacio")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate birthdate;

    @NotNull(message = "El campo no puede estar vacio")
    private Gender gender;

    private Role role = Role.getDefaultRole();
}
