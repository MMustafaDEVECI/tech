package com.example.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.example.Dto.MatchResponseDto;
import com.example.Dto.PlayerRequestDto;
import com.example.Dto.PlayerResponseDto;
import com.example.Dto.RankingResponseDto;
import com.example.Entity.GamePlayer;
import com.example.Entity.GamePlayerId;
import com.example.Entity.Match;
import com.example.Entity.MatchEndedEvent;
import com.example.Entity.MatchPlayer;
import com.example.Entity.Player;
import com.example.Repository.GamePlayerRepository;
import com.example.Repository.MatchPlayerRepository;
import com.example.Repository.MatchRepository;
import com.example.Repository.PlayerRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final GamePlayerRepository gamePlayerRepository;
    private final MatchPlayerRepository matchPlayerRepository;
    private final MatchRepository matchRepository;

    /* register
     * enterQueue publish event
     * getlast20matches in a game
     * getRankinginaGame
     * deleteAccount
     * getAccountInformation
     */
    public Player register(PlayerRequestDto playerRequestDto) {
        Player player = toRequestDto(playerRequestDto);
        return playerRepository.save(player);
    }
    public void deleteAccount(Long playerId) {
        playerRepository.deleteById(playerId);
    }
    public RankingResponseDto getRankingInAGame(Long playerId, Long gameId) {
        GamePlayerId gamePlayerId = new GamePlayerId(gameId, playerId);
        GamePlayer gamePlayer = gamePlayerRepository.findById(gamePlayerId)
                .orElseThrow(() -> new NoSuchElementException("GamePlayer not found"));
        return toRankingResponseDto(gamePlayer);
    }
    public List<MatchResponseDto> getLast20MatchesInGame(Long playerId, Long gameId) {
        List<Match> matches = matchRepository.findTop20ByGameIdAndPlayerIdOrderByMatchTimeDesc(gameId, playerId);
        return matches.stream()
                .map(this::toMatchResponseDto)
                .collect(Collectors.toList());
    }
    public PlayerResponseDto getAccountInformation(Long playerId) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new NoSuchElementException("Player not found"));
        return toResponseDto(player);
    }

    @EventListener
    @Transactional
    public void onMatchEnded(MatchEndedEvent event) {
        Long matchId = event.getMatchId();
        List<MatchPlayer> matchPlayers = matchPlayerRepository.findMatchPlayerByMatchId(matchId);

        for (MatchPlayer matchPlayer : matchPlayers) {
            GamePlayerId gamePlayerId = new GamePlayerId(matchRepository.findGameIdByMatchId(matchPlayer.getId().getMatchId()), matchPlayer.getId().getPlayerId());
            GamePlayer gamePlayer = gamePlayerRepository.findById(gamePlayerId)
                    .orElseThrow(() -> new NoSuchElementException("GamePlayer not found"));

            
            if (matchPlayer.getScore() > 0) {  
                gamePlayer.setWinNumber(gamePlayer.getWinNumber() + 1);
            } else {
                gamePlayer.setLoseNumber(gamePlayer.getLoseNumber() + 1);
            }

            gamePlayer.setTimePlayed(LocalDateTime.now());

            gamePlayerRepository.save(gamePlayer);
        }
    }
    private Player toRequestDto(PlayerRequestDto playerRequestDto) {
        Player player = new Player();
        player.setNick(playerRequestDto.getNick());
        player.setLevel(playerRequestDto.getLevel());
        player.setXp(playerRequestDto.getXp());
        player.setCountry(playerRequestDto.getCountry());
        return player;
    }
    private PlayerResponseDto toResponseDto(Player player) {
        return new PlayerResponseDto(
                player.getPlayerId(),
                player.getNick(),
                player.getLevel(),
                player.getXp(),
                player.getCountry()
        );
    }
    private RankingResponseDto toRankingResponseDto(GamePlayer gamePlayer) {
        return new RankingResponseDto(
                gamePlayer.getId().getPlayerId(),
                playerRepository.findById(gamePlayer.getId().getPlayerId())
                        .map(Player::getNick)
                        .orElse("Unknown Player"),
                gamePlayer.getRank()
        );
    }
    private MatchResponseDto toMatchResponseDto(Match match) {
        return new MatchResponseDto(
                match.getMatchId(),
                match.getMatchTime(),
                match.getDuration(),
                match.getGameId()
        );
    }
    
}
