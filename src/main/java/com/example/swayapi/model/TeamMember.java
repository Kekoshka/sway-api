package com.example.swayapi.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "team_members")
public class TeamMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "team_id")
    private Long teamId;
    @Column(name = "user_id")
    private String userId;
    private String name;
    @Column(name = "photo_url", columnDefinition = "TEXT")
    private String photoUrl;
    private String role;
    private String position;
    @Column(name = "block_games")
    private boolean blockGames;
    @Column(name = "block_social")
    private boolean blockSocial;
    @Column(name = "block_entertainment")
    private boolean blockEntertainment;
    @Column(name = "block_messengers")
    private boolean blockMessengers;
}