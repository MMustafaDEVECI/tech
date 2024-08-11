package com.example.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
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
    public Optional<Game> getGamebyId(long id){
        return gameRepository.findById(id);
    }
    public void updateGame(long id){
        Optional<Game> game = gameRepository.findById(id);
        if(game.isPresent()){
            game.get().setGameName("Valo");
            gameRepository.save(game.get());
        }
    }
    public List<GamePlayerResponseDto> getTopPlayers(long id){
        List<GamePlayerResponseDto> topPlayers = new ArrayList<>();
        Optional<Game> game = gameRepository.findById(id);
        if(game.isPresent()){
            List<GamePlayer> gamePlayers = gamePlayerRepository.findTop10ByGameIdOrderByWins(id);
            for(GamePlayer gamePlayer: gamePlayers){
                GamePlayerResponseDto gamePlayerResponseDto = new GamePlayerResponseDto();
                Long playerId = gamePlayer.getId().getPlayerId();
                Optional<Player> player = playerRepository.findById(playerId);
                if(player.isPresent()){
                    gamePlayerResponseDto.setPlayerId(playerId);
                    gamePlayerResponseDto.setPlayerName(player.get().getNick());
                    gamePlayerResponseDto.setDuration(gamePlayer.getTimePlayed());
                    gamePlayerResponseDto.setFirstPlayed(gamePlayer.getFirstPlayed());
                }
                topPlayers.add(gamePlayerResponseDto);
            }

        }
        return topPlayers;
    }

}