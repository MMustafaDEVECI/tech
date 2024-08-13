package com.example.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Entity.MatchPlayer;
import com.example.Entity.MatchPlayerId;

@Repository
public interface MatchPlayerRepository extends JpaRepository<MatchPlayer, MatchPlayerId>{
    List<Long> findPlayerIdByMatchId(Long matchId);
    List<MatchPlayer> findMatchPlayerByMatchId(Long matchId);
}
