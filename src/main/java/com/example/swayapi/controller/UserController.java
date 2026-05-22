package com.example.swayapi.controller;

import com.example.swayapi.model.TeamMember;
import com.example.swayapi.model.User;
import com.example.swayapi.repository.TeamMemberRepository;
import com.example.swayapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository repo;
    private final TeamMemberRepository teamMemberRepo;

    // Создать или обновить пользователя при входе
    @PostMapping
    public ResponseEntity<User> upsert(@RequestBody User user) {
        User saved = repo.save(user);

        // Каскадно обновляем имя и фото во всех командах, где состоит этот пользователь
        List<TeamMember> memberships = teamMemberRepo.findByUserId(saved.getId());
        for (TeamMember m : memberships) {
            boolean changed = false;
            if (saved.getName() != null && !saved.getName().equals(m.getName())) {
                m.setName(saved.getName());
                changed = true;
            }
            if (saved.getPhotoUrl() != null && !saved.getPhotoUrl().equals(m.getPhotoUrl())) {
                m.setPhotoUrl(saved.getPhotoUrl());
                changed = true;
            }
            if (changed) {
                teamMemberRepo.save(m);
            }
        }

        return ResponseEntity.ok(saved);
    }

    // Получить пользователя
    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable String id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
