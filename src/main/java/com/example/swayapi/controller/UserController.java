package com.example.swayapi.controller;

import com.example.swayapi.model.User;
import com.example.swayapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository repo;

    // Создать или обновить пользователя при входе
    @PostMapping
    public ResponseEntity<User> upsert(@RequestBody User user) {
        return ResponseEntity.ok(repo.save(user));
    }

    // Получить пользователя
    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable String id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
