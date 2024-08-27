package com.example.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Entity.MatchPlayer;
import com.example.Entity.MatchPlayerId;

@Repository
public interface MatchPlayerRepository extends JpaRepository<MatchPlayer, MatchPlayerId>{
    @Query(value = "SELECT player_id FROM MatchPlayer WHERE match_id = :matchId", nativeQuery = true)
    List<Long> findPlayerIdByMatchId(@Param("matchId") Long matchId);

    @Query(value = "SELECT * FROM MatchPlayer WHERE match_id = :matchId", nativeQuery = true)
    List<MatchPlayer> findMatchPlayerByMatchId(@Param("matchId") Long matchId);
}
