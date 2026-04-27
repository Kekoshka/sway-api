package com.example.swayapi.controller;

import com.example.swayapi.model.Task;
import com.example.swayapi.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskRepository repo;

    @PostMapping
    public ResponseEntity<Task> create(@RequestBody Task task) {
        return ResponseEntity.ok(repo.save(task));
    }

    @GetMapping("/user/{userId}")
    public List<Task> getByUser(@PathVariable String userId,
                                @RequestParam String date) {
        return repo.findByToUserIdAndDate(userId, date);
    }

    @GetMapping("/team/{teamId}")
    public List<Task> getByTeam(@PathVariable Long teamId,
                                @RequestParam String date) {
        return repo.findByTeamIdAndDate(teamId, date);
    }

    @PutMapping("/{id}/done")
    public ResponseEntity<Task> markDone(@PathVariable Long id) {
        return repo.findById(id).map(task -> {
            task.setDone(true);
            task.setDoneAtMs(System.currentTimeMillis());
            return ResponseEntity.ok(repo.save(task));
        }).orElse(ResponseEntity.notFound().build());
    }
}
