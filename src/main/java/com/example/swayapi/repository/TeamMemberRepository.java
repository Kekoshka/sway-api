package com.example.swayapi.repository;

import com.example.swayapi.model.TeamMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

public interface TeamMemberRepository extends JpaRepository<TeamMember, Long> {
    List<TeamMember> findByTeamId(Long teamId);

    // Один любой по userId (если где-то используется — оставляем под другим именем)
    Optional<TeamMember> findFirstByUserId(String userId);

    // Все записи по userId (для каскадного обновления)
    List<TeamMember> findAllByUserId(String userId);

    Optional<TeamMember> findByTeamIdAndUserId(Long teamId, String userId);

    @Modifying
    @Transactional
    @Query("DELETE FROM TeamMember m WHERE m.teamId = :teamId")
    void deleteByTeamId(Long teamId);
}
