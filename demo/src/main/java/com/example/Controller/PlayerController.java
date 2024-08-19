package com.example.Controller;

import com.example.Dto.*;
import com.example.Service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/players")
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService playerService;
    @PostMapping("/register")
    public ResponseEntity<PlayerResponseDto> register(@RequestBody PlayerRequestDto playerRequestDto) {
        PlayerResponseDto playerResponseDto = playerService.register(playerRequestDto);
        return ResponseEntity.ok(playerResponseDto);
    }
    @GetMapping("/matches/last20")
    public ResponseEntity<List<MatchResponseDto>> getLast20MatchesInGame(@RequestParam Long playerId, @RequestParam Long gameId) {
        List<MatchResponseDto> matches = playerService.getLast20MatchesInGame(playerId, gameId);
        return ResponseEntity.ok(matches);
    }
    @GetMapping("/ranking")
    public ResponseEntity<RankingResponseDto> getRankingInAGame(@RequestParam Long playerId, @RequestParam Long gameId) {
        RankingResponseDto ranking = playerService.getRankingInAGame(playerId, gameId);
        return ResponseEntity.ok(ranking);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteAccount(@RequestParam Long playerId) {
        playerService.deleteAccount(playerId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/account")
    public ResponseEntity<PlayerResponseDto> getAccountInformation(@RequestParam Long playerId) {
        PlayerResponseDto playerInfo = playerService.getAccountInformation(playerId);
        return ResponseEntity.ok(playerInfo);
    }

    @PostMapping("/queue")
    public ResponseEntity<Void> enterQueue(@RequestParam Long playerId, @RequestParam Long gameId) {
        playerService.enterQueue(playerId, gameId);
        return ResponseEntity.ok().build();
    }
}

