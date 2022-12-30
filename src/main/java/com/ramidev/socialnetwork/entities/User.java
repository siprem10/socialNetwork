package com.ramidev.socialnetwork.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ramidev.socialnetwork.enums.Gender;
import com.ramidev.socialnetwork.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDate;

@Entity
@Table(name = "user")
@Getter @Setter @ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "firstname")
    private String firstname;

    @NotNull
    @Column(name = "lastname")
    private String lastname;

    @NotNull
    @Email
    @Column(name = "email", unique = true)
    private String email;

    @NotNull
    @Column(name = "password")
    private String password;

    @NotNull
    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(name = "birthdate")
    private LocalDate birthdate;

    @NotNull
    @Column(name = "gender")
    private Gender gender;

    @NotNull
    @Column(name = "role")
    private Role role = Role.getDefaultRole();

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    private Profile profile;
}
