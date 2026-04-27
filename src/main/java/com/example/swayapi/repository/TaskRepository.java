package com.example.swayapi.repository;

import com.example.swayapi.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByToUserIdAndDate(String toUserId, String date);
    List<Task> findByTeamIdAndDate(Long teamId, String date);
}
