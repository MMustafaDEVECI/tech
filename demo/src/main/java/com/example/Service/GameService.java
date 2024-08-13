package com.example.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.example.Dto.GameAddRequestDto;
import com.example.Dto.GamePlayerResponseDto;
import com.example.Entity.Game;
import com.example.Entity.GamePlayer;
import com.example.Entity.Player;
import com.example.Repository.GamePlayerRepository;
import com.example.Repository.GameRepository;
import com.example.Repository.PlayerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GameService {
    
    private final GameRepository gameRepository;
    private final GamePlayerRepository gamePlayerRepository;
    private final PlayerRepository playerRepository;

    public Boolean addGame(GameAddRequestDto gameAddRequestDto){

        Game game = new Game();
        game.setGameName(gameAddRequestDto.getName());
        game.setTeamSize(gameAddRequestDto.getSize());
        game.setYear(gameAddRequestDto.getYear());
        gameRepository.save(game);
        return true;
    }
    
    public Boolean deleteGame(Long gameId){
        gameRepository.deleteById(gameId);
        return true;
    }
    
    public List<Game> getGames(){
        return gameRepository.findAll();
    }
    public Optional<Game> getGamebyId(Long id){
        return gameRepository.findById(id);
    }
    public void updateGame(Long id){
        Optional<Game> game = gameRepository.findById(id);
        if(game.isPresent()){
            game.get().setGameName("Valo");
            gameRepository.save(game.get());
        }
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
            GamePlayerResponseDto gamePlayerResponseDto = toDto(gamePlayer, player);
            topPlayers.add(gamePlayerResponseDto);
        }
        return topPlayers;


    }
    private Optional<Player> findPlayerByGamePlayer(GamePlayer gamePlayer){
        Long playerId = gamePlayer.getId().getPlayerId();
        return playerRepository.findById(playerId);
    }

    private GamePlayerResponseDto toDto(GamePlayer gamePlayer, Optional<Player> player) {
        GamePlayerResponseDto dto = new GamePlayerResponseDto();
        dto.setPlayerId(player.get().getPlayerId());
        dto.setPlayerName(player.get().getNick());
        dto.setDuration(gamePlayer.getTimePlayed());
        dto.setFirstPlayed(gamePlayer.getFirstPlayed());
        return dto;
    }

}