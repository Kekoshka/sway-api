package com.example.swayapi.controller;

import com.example.swayapi.model.Team;
import com.example.swayapi.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.swayapi.repository.TeamMemberRepository;
import com.example.swayapi.repository.ShiftRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@RestController
@RequestMapping("/api/teams")
@RequiredArgsConstructor
public class TeamController {
    private final TeamRepository repo;
    private final TeamMemberRepository memberRepo;  
    private final ShiftRepository shiftRepo; 

    @PostMapping
    public ResponseEntity<Team> create(@RequestBody Team team) {
        return ResponseEntity.ok(repo.save(team));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Team> getById(@PathVariable Long id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<Team> getByCode(@PathVariable String code) {
        return repo.findByCode(code)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Team> update(@PathVariable Long id,
                                       @RequestBody Team team) {
        team.setId(id);
        return ResponseEntity.ok(repo.save(team));
    }

    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<Team>> getAllByOwner(@PathVariable String ownerId) {
        return ResponseEntity.ok(repo.findAllByOwnerId(ownerId));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            memberRepo.deleteByTeamId(id);
            repo.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            System.out.println("DELETE TEAM ERROR: " + e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}
