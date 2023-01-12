package com.ramidev.socialnetwork.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "profile")
@Getter @Setter @ToString
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "profile_pic")
    private String profilePic;

    @Column(name = "cover_photo")
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

    @NotNull
    @OneToOne(mappedBy = "profile")
    private User user;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<FriendShip> friendShips = new HashSet<>();

    @JsonBackReference
    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Post> posts = new HashSet<>();

    public void addFriendShip(FriendShip friendShip) {
        friendShips.add(friendShip);
        friendShip.setProfile(this);
    }
    public void removeFriendShip(FriendShip friendShip) {
        friendShips.remove(friendShip);
        friendShip.setProfile(null);
    }

    public void addPost(Post post) {
        posts.add(post);
        post.setProfile(this);
    }
    public void removePost(Post post) {
        posts.remove(post);
        post.setProfile(null);
    }
    public Profile() {
    }
    public Profile(User user) {
        this.user = user;
    }
}
