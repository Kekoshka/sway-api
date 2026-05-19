package com.example.swayapi.repository;

import com.example.swayapi.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
public interface TeamRepository extends JpaRepository<Team, Long> {
    Optional<Team> findByCode(String code);
    Optional<Team> findByOwnerId(String ownerId);
    List<Team> findAllByOwnerId(String ownerId);
}
