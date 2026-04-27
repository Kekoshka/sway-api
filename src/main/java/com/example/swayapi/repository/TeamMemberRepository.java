package com.example.swayapi.repository;

import com.example.swayapi.model.TeamMember;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
public interface TeamMemberRepository extends JpaRepository<TeamMember, Long> {
    List<TeamMember> findByTeamId(Long teamId);
    Optional<TeamMember> findByUserId(String userId);
    Optional<TeamMember> findByTeamIdAndUserId(Long teamId, String userId);
}
