package com.ramidev.socialnetwork.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "profile")
@Getter @Setter @ToString
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "profilePic")
    private String profilePic;

    @Column(name = "coverPhoto")
    private String coverPhoto;

    @Column(name = "description")
    private String description;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "hobbie")
    private String hobbie;

    @Column(name = "job")
    private String job;
}
