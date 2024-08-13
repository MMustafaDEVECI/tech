package com.example.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Entity.Match;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long>{
    Long findGameIdByMatchId(Long matchId);
    List<Match> findTop20ByGameIdAndPlayerIdOrderByMatchTimeDesc(Long gameId, Long playerId);
}

