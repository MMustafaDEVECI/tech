package com.example.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Entity.GamePlayer;
import com.example.Entity.GamePlayerId;

@Repository
public interface GamePlayerRepository extends JpaRepository<GamePlayer, GamePlayerId>{
    List<GamePlayer> findByIdPlayerId(Long playerId);
    List<GamePlayer> findByIdGameId(Long gameId);
    @Query(value = "SELECT * FROM GamePlayer gp WHERE gp.game_id = :gameId ORDER BY gp.win_number DESC LIMIT 10", nativeQuery = true)
    List<GamePlayer> findTop10ByGameIdOrderByWins(@Param("gameId") Long gameId);
}
