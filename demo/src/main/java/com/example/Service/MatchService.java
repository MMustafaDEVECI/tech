package com.example.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.example.Entity.Match;
import com.example.Entity.MatchEndedEvent;
import com.example.Entity.MatchPlayer;
import com.example.Entity.MatchPlayerId;
import com.example.Entity.MatchReadyEvent;
import com.example.Repository.MatchPlayerRepository;
import com.example.Repository.MatchRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MatchService {
    
    private final MatchRepository matchRepository;
    private final MatchPlayerRepository matchPlayerRepository;
    private final ApplicationEventPublisher eventPublisher;

    @EventListener
    public void onMatchReady(MatchReadyEvent event) {
        startMatchWithPlayers(event.getGameId(), event.getPlayerIds());
    }

    public void startMatchWithPlayers(Long gameId, List<Long> playerIds) {
        Match match = createMatch(gameId, LocalDateTime.now(), 0);
        for (Long playerId : playerIds) {
            MatchPlayer matchPlayer = new MatchPlayer(new MatchPlayerId(match.getMatchId(), playerId), 0l, 0l, 0l, 0l);
            matchPlayerRepository.save(matchPlayer);
        }
    }

    public Match createMatch(Long gameId, LocalDateTime matchTime, long duration) {
        Match match = new Match();
        match.setGameId(gameId);
        match.setMatchTime(matchTime);
        match.setDuration(duration);
        return matchRepository.save(match);
    }
    @Transactional
    public Match endMatch(Long matchId, long duration) {
        Match match = matchRepository.findById(matchId)
                .orElseThrow(() -> new NoSuchElementException("Match not found"));

        match.setDuration(duration);
        Match savedMatch = matchRepository.save(match);

        List<Long> playerIds = matchPlayerRepository.findPlayerIdByMatchId(matchId);

        eventPublisher.publishEvent(new MatchEndedEvent(this, matchId, playerIds));

        return savedMatch;
    }
}
