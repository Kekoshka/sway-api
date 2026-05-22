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

    @PostMapping
    public ResponseEntity<User> upsert(@RequestBody User user) {
        System.out.println(">>> UserController.upsert CALLED: userId=" + user.getId() 
            + " name=" + user.getName());
        
        User saved = repo.save(user);
        System.out.println(">>> User saved");

        List<TeamMember> memberships = teamMemberRepo.findByUserId(saved.getId());
        System.out.println(">>> Found memberships: " + memberships.size());
        
        for (TeamMember m : memberships) {
            System.out.println(">>> Updating member id=" + m.getId() 
                + " oldName=" + m.getName() + " newName=" + saved.getName());
            
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
                System.out.println(">>> Member saved");
            } else {
                System.out.println(">>> No changes");
            }
        }

        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable String id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
