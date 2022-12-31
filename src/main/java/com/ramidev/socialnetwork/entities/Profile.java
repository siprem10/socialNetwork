package com.ramidev.socialnetwork.entities;

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
    @NotNull
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Post> posts = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

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
