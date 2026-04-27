package com.example.swayapi.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "team_id")
    private Long teamId;
    @Column(name = "from_user_id")
    private String fromUserId;
    @Column(name = "from_user_name")
    private String fromUserName;
    @Column(name = "to_user_id")
    private String toUserId;
    private String title;
    private String date;
    @Column(name = "is_done")
    private boolean isDone;
    @Column(name = "created_at_ms")
    private Long createdAtMs;
    @Column(name = "done_at_ms")
    private Long doneAtMs;
}