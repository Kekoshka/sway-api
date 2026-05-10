package com.example.swayapi.controller;

import com.example.swayapi.model.Shift;
import com.example.swayapi.repository.ShiftRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/shifts")
@RequiredArgsConstructor
public class ShiftController {
    private final ShiftRepository repo;

    @PostMapping
    public ResponseEntity<Shift> save(@RequestBody Shift shift) {
        return ResponseEntity.ok(repo.save(shift));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Shift> update(@PathVariable Long id,
                                        @RequestBody Shift shift) {
        shift.setId(id);
        return ResponseEntity.ok(repo.save(shift));
    }

    @GetMapping("/team/{teamId}")
    public ResponseEntity<List<Shift>> getTeamShifts(
        @PathVariable Long teamId,
        @RequestParam String date) {
        List<Shift> shifts = shiftRepository
            .findByTeamIdAndDate(teamId, date);
        return ResponseEntity.ok(shifts);
    }

    @GetMapping("/user/{userId}")
    public List<Shift> getByUser(@PathVariable String userId) {
        return repo.findByUserIdOrderByDateDesc(userId);
    }
}
