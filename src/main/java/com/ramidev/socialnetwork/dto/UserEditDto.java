package com.ramidev.socialnetwork.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ramidev.socialnetwork.enums.Gender;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter @Setter @ToString
public class UserEditDto {

    private Long id;

    @NotBlank(message = "El campo no puede estar vacio")
    @Size(min = 3, message = "El campo debe contener al menos 3 caracteres")
    @Size(max = 20, message = "El campo no puede superar los 20 caracteres")
    private String firstname;

    @NotBlank(message = "El campo no puede estar vacio")
    @Size(min = 3, message = "El campo debe contener al menos 3 caracteres")
    @Size(max = 20, message = "El campo no puede superar los 20 caracteres")
    private String lastname;

    @NotNull(message = "El campo no puede estar vacio")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate birthdate;

    @NotNull(message = "El campo no puede estar vacio")
    private Gender gender;
}
