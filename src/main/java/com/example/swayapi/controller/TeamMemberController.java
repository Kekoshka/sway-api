package com.example.swayapi.controller;

import com.example.swayapi.model.TeamMember;
import com.example.swayapi.repository.TeamMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class TeamMemberController {
    private final TeamMemberRepository repo;

    @PostMapping
    public ResponseEntity<TeamMember> add(@RequestBody TeamMember member) {
        repo.findByTeamIdAndUserId(member.getTeamId(), member.getUserId())
                .ifPresent(existing -> member.setId(existing.getId()));
        return ResponseEntity.ok(repo.save(member));
    }

    @GetMapping("/team/{teamId}")
    public List<TeamMember> getByTeam(@PathVariable Long teamId) {
        return repo.findByTeamId(teamId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeamMember> update(@PathVariable Long id,
                                             @RequestBody TeamMember member) {
        member.setId(id);
        return ResponseEntity.ok(repo.save(member));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        repo.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
