package com.example.Repository;


import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Entity.Match;
import java.time.LocalDateTime;


@Repository
public interface MatchRepository extends JpaRepository<Match, Long>{
    Long findGameIdByMatchId(Long matchId);
    List<Match> findTop20ByGameIdAndPlayerIdOrderByMatchTimeDesc(Long gameId, Long playerId);
    @Query("SELECT COUNT(m) FROM Match m WHERE m.matchTime BETWEEN :startOfDay AND :endOfDay")
    int findMatchesByDay(@Param("startOfDay") LocalDateTime startOfDay, @Param("endOfDay") LocalDateTime endOfDay);
    @Query("SELECT COUNT(m) FROM Match m WHERE m.duration = 0")
    int findNumberOfCurrentMatches();
    @Query("SELECT COUNT(m) FROM Match m WHERE game_id = :gameId AND BETWEEN :startOfDay AND :endOfDay")
    int findNumberofMatchesToday(@Param("gameId") Long gameId, @Param("startOfDay") LocalDateTime startOfDay, @Param("endOfDay") LocalDateTime endOfDay);
}

