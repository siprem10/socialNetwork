package com.ramidev.socialnetwork.dto.profile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ramidev.socialnetwork.entities.FriendShip;
import com.ramidev.socialnetwork.entities.Post;
import com.ramidev.socialnetwork.entities.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Getter @Setter @ToString
public class ProfileDto {

    private Long id;
    private String profilePic;
    private String coverPhoto;
    private String description;
    private String country;
    private String city;
    private String hobbie;
    private String job;
    @JsonIgnoreProperties("password")
    private User user;
    private Set<Post> posts = new HashSet<>();
    private Set<FriendShip> friendShips = new HashSet<>();
    private Integer friendShips2 = friendShips.size();
}
