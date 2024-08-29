package com.example.Service;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.example.Dto.GamePlayerResponseDto;
import com.example.Dto.GameRequestDto;
import com.example.Dto.GameResponseDto;
import com.example.Entity.Game;
import com.example.Entity.GamePlayer;
import com.example.Entity.Player;
import com.example.Repository.GamePlayerRepository;
import com.example.Repository.GameRepository;
import com.example.Repository.MatchRepository;
import com.example.Repository.PlayerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GameService {
    
    private final GameRepository gameRepository;
    private final GamePlayerRepository gamePlayerRepository;
    private final PlayerRepository playerRepository;
    private final MatchRepository matchRepository;

    public Boolean addGame(GameRequestDto gameAddRequestDto){

        Game game = toRequestDto(gameAddRequestDto);
        gameRepository.save(game);
        return true;
    }
    

    public Boolean deleteGame(Long gameId){
        gameRepository.deleteById(gameId);
        return true;
    }
    
    public List<GameResponseDto> getGames(){
        List<Game> games = gameRepository.findAll();
        return toResponseDtoList(games);

    }
    public GameResponseDto getGameById(Long id){
        Game game = gameRepository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("Game not found"));
        return gameToResponseDto(game);
    }
    public void updateGame(Long id, GameRequestDto gameRequestDto){
        Boolean ifExists = gameRepository.existsById(id);
        if(ifExists){
            Game game = toRequestDto(gameRequestDto);
            game.setGameId(id);
            gameRepository.save(game);
        }
    }
    public int getNumberofMatchesToday(Long gameId){
        LocalDateTime todayBeginning = LocalDate.now().atStartOfDay();
        LocalDateTime todayEnd = LocalDate.now().atTime(LocalTime.MAX);
        return matchRepository.findNumberofMatchesToday(gameId, todayBeginning, todayEnd);
    }
    public int getNumberOfPlayersOfGame(Long gameId){
        return gamePlayerRepository.findNumberOfPlayersOfGame(gameId);
    }
    public List<GamePlayerResponseDto> getTopPlayers(Long id){

        List<GamePlayer> gamePlayers = gamePlayerRepository.findTop10ByGameIdOrderByWins(id);
        List<GamePlayerResponseDto> topPlayers = convertGamePlayersToResponse(gamePlayers);
        return topPlayers;
    }

    private List<GamePlayerResponseDto> convertGamePlayersToResponse(List<GamePlayer> gamePlayers){
        
        List<GamePlayerResponseDto> topPlayers = new ArrayList<>();
        for(GamePlayer gamePlayer: gamePlayers){ 
            Optional<Player> player = findPlayerByGamePlayer(gamePlayer);
            GamePlayerResponseDto gamePlayerResponseDto = toResponseDto(gamePlayer, player);
            topPlayers.add(gamePlayerResponseDto);
        }
        return topPlayers;


    }
    private Optional<Player> findPlayerByGamePlayer(GamePlayer gamePlayer){
        Long playerId = gamePlayer.getId().getPlayerId();
        return playerRepository.findById(playerId);
    }

    private GamePlayerResponseDto toResponseDto(GamePlayer gamePlayer, Optional<Player> player) {
        GamePlayerResponseDto dto = new GamePlayerResponseDto();
        dto.setPlayerId(player.get().getPlayerId());
        dto.setPlayerName(player.get().getNick());
        dto.setDuration(gamePlayer.getTimePlayed());
        dto.setFirstPlayed(gamePlayer.getFirstPlayed());
        return dto;
    }

    private Game toRequestDto(GameRequestDto gameAddRequestDto) {
        Game game = new Game();
        game.setGameName(gameAddRequestDto.getName());
        game.setTeamSize(gameAddRequestDto.getSize());
        game.setYear(gameAddRequestDto.getYear());
        return game;
    }
    private List<GameResponseDto> toResponseDtoList(List<Game> games){
        List<GameResponseDto> gameResponseDtos = new ArrayList<>();
        for(Game game: games){
            gameResponseDtos.add(gameToResponseDto(game));
        }
        return gameResponseDtos;
    }
    private GameResponseDto gameToResponseDto(Game game) {
        GameResponseDto dto = new GameResponseDto();
        dto.setGameId(game.getGameId());
        dto.setName(game.getGameName());
        dto.setTeamSize(game.getTeamSize());
        dto.setYear(game.getYear());
        return dto;
    }

}