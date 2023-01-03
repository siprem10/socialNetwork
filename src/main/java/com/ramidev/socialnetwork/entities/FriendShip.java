package com.ramidev.socialnetwork.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "friendship")
@Getter @Setter @ToString
public class FriendShip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "sender_id")
    private Integer senderId;
    @Column(name = "receiver_id")
    private Integer receiverId;
    @Column(name = "is_acepted")
    private boolean isAccepted;
}
