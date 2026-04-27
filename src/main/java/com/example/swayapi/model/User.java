package com.example.swayapi.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    private String id;
    private String email;
    private String name;
    @JsonProperty("photo_url")
    @Column(name = "photo_url", columnDefinition = "TEXT")
    private String photoUrl;
    private String provider;
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
}
