package com.example.Controller;

import com.example.Dto.GamePlayerResponseDto;
import com.example.Dto.GameRequestDto;
import com.example.Dto.GameResponseDto;

import com.example.Service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/games")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @PostMapping
    public ResponseEntity<Boolean> addGame(@RequestBody GameRequestDto gameRequestDto) {
        Boolean createdGame = gameService.addGame(gameRequestDto);
        return ResponseEntity.ok(createdGame);
    }

    @PutMapping("/{gameId}")
    public ResponseEntity<String> editGame(@PathVariable Long gameId, @RequestBody GameRequestDto gameRequestDto) {
        gameService.updateGame(gameId, gameRequestDto);
        return ResponseEntity.ok("Update succeded");
    }

    @DeleteMapping("/{gameId}")
    public ResponseEntity<Void> deleteGame(@PathVariable Long gameId) {
        gameService.deleteGame(gameId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<GameResponseDto>> getGames() {
        List<GameResponseDto> games = gameService.getGames();
        return ResponseEntity.ok(games);
    }

    @GetMapping("/{gameId}")
    public ResponseEntity<GameResponseDto> getGameById(@PathVariable Long gameId) {
        GameResponseDto gameResponse = gameService.getGameById(gameId);
        return ResponseEntity.ok(gameResponse);
    }

    @GetMapping("/{gameId}/numberOfMatches")
    public ResponseEntity<Integer> getMatchPlayersToday(@PathVariable Long gameId) {
        int matchNumber = gameService.getNumberofMatchesToday(gameId);
        return ResponseEntity.ok(matchNumber);
    }

    @GetMapping("/{gameId}/numberOfPlayers")
    public ResponseEntity<Integer> getNumberOfPlayersOfGame(@PathVariable Long gameId) {
        int playerCount = gameService.getNumberOfPlayersOfGame(gameId);
        return ResponseEntity.ok(playerCount);
    }
    @GetMapping("/{gameId}/bestPlayers")
    public ResponseEntity<List<GamePlayerResponseDto>> getBestPlayers(@PathVariable Long gameId) {
        List<GamePlayerResponseDto> bestPlayers = gameService.getTopPlayers(gameId);
        return ResponseEntity.ok(bestPlayers);
    }
    
}
