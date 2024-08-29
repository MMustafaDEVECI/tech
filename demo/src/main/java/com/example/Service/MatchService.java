package com.example.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.example.Dto.MatchResponseDto;
import com.example.Entity.GamePlayer;
import com.example.Entity.GamePlayerId;
import com.example.Entity.Match;
import com.example.Entity.MatchEndedEvent;
import com.example.Entity.MatchPlayer;
import com.example.Entity.MatchPlayerId;
import com.example.Entity.MatchReadyEvent;
import com.example.Repository.GamePlayerRepository;
import com.example.Repository.MatchPlayerRepository;
import com.example.Repository.MatchRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MatchService {
    
    private final MatchRepository matchRepository;
    private final GamePlayerRepository gamePlayerRepository;
    private final MatchPlayerRepository matchPlayerRepository;
    private final ApplicationEventPublisher eventPublisher;

    @EventListener
    public void onMatchReady(MatchReadyEvent event) {
        startMatchWithPlayers(event.getGameId(), event.getPlayerIds());
    }

    private void startMatchWithPlayers(Long gameId, List<Long> playerIds) {
        Match match = createMatch(gameId, LocalDateTime.now(), 0);
        for (Long playerId : playerIds) {
            MatchPlayer matchPlayer = new MatchPlayer(new MatchPlayerId(match.getMatchId(), playerId), 0l, 0l, 0l, 0l);
            if(!gamePlayerRepository.existsById(new GamePlayerId(gameId, playerId))){
                /* setGamePlayer new function SOLID */
                GamePlayer gamePlayer = new GamePlayer();
                gamePlayer.setFirstPlayed(LocalDateTime.now());
                gamePlayer.setId(new GamePlayerId(gameId, playerId));
                gamePlayer.setWinNumber(0l);
                gamePlayer.setDrawNumber(0l);
                gamePlayer.setLoseNumber(0l);
                gamePlayerRepository.save(gamePlayer);
            }
            matchPlayerRepository.save(matchPlayer);
            System.out.println(playerId + " added");
        }
    }

    public MatchResponseDto getMatchInfo(Long id){
        Match match = matchRepository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("Match not found"));
        return toResponseDto(match);

    }

    public int getNumberOfMatchesByDay(LocalDate date){
        LocalDateTime beginning =  convertDateToTimeBeginning(date);
        LocalDateTime end =  convertDateToTimeEnd(date);
        int numberOfMatches = matchRepository.findMatchesByDay(beginning, end);
        return numberOfMatches;
    }

    public int getNumberOfCurrentMatches(){
        return matchRepository.findNumberOfCurrentMatches();
    }

    private LocalDateTime convertDateToTimeBeginning(LocalDate date) {
        return date.atStartOfDay();
    }
    private LocalDateTime convertDateToTimeEnd(LocalDate date) {
        return date.atTime(LocalTime.MAX);
    }

    private Match createMatch(Long gameId, LocalDateTime matchTime, long duration) {
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

    private MatchResponseDto toResponseDto(Match match){
        
        MatchResponseDto matchResponseDto = new MatchResponseDto();
        matchResponseDto.setMatchId(match.getMatchId());
        matchResponseDto.setGameId(match.getGameId());
        matchResponseDto.setMatchTime(match.getMatchTime());
        matchResponseDto.setDuration(match.getDuration());
        return matchResponseDto;
    }
}
