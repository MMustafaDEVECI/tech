package com.example.Repository;


import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Entity.Match;
import java.time.LocalDateTime;


@Repository
public interface MatchRepository extends JpaRepository<Match, Long>{
    @Query(value = "SELECT game_id FROM `Match` WHERE match_id = :matchId", nativeQuery = true)
    Long findGameIdByMatchId(@Param("matchId") Long matchId);

    @Query(value = "SELECT * FROM `Match` WHERE game_id = :gameId AND match_id IN (SELECT match_id FROM MatchPlayer WHERE player_id = :playerId) ORDER BY match_time DESC LIMIT 20", nativeQuery = true)
    List<Match> findTop20MatchesByGameIdAndPlayerIdOrderByMatchTimeDesc(@Param("gameId") Long gameId, @Param("playerId") Long playerId);

    @Query(value = "SELECT COUNT(*) FROM `Match` WHERE match_time BETWEEN :startOfDay AND :endOfDay", nativeQuery = true)
    int findMatchesByDay(@Param("startOfDay") LocalDateTime startOfDay, @Param("endOfDay") LocalDateTime endOfDay);

    @Query(value = "SELECT COUNT(*) FROM `Match` WHERE duration = 0", nativeQuery = true)
    int findNumberOfCurrentMatches();

    @Query(value = "SELECT COUNT(*) FROM `Match` WHERE game_id = :gameId AND match_time BETWEEN :startOfDay AND :endOfDay", nativeQuery = true)
    int findNumberofMatchesToday(@Param("gameId") Long gameId, @Param("startOfDay") LocalDateTime startOfDay, @Param("endOfDay") LocalDateTime endOfDay);
}
    

