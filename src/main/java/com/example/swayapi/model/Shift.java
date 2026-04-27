package com.example.swayapi.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "shifts")
public class Shift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "team_id")
    private Long teamId;
    private String date;
    @Column(name = "planned_start")
    private String plannedStart;
    @Column(name = "planned_end")
    private String plannedEnd;
    @Column(name = "actual_start_ms")
    private Long actualStartMs;
    @Column(name = "break_start_ms")
    private Long breakStartMs;
    @Column(name = "break_end_ms")
    private Long breakEndMs;
    @Column(name = "actual_end_ms")
    private Long actualEndMs;
    private String status;
}