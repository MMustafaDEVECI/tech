package com.example.Controller;

import java.time.LocalDate;

//import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Dto.MatchIdRequestDto;
import com.example.Dto.MatchResponseDto;
import com.example.Service.MatchService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/matches")
@RequiredArgsConstructor
public class MatchController {

    private final MatchService matchService;

    @GetMapping
    public ResponseEntity<MatchResponseDto> getMatchInfo(@RequestBody MatchIdRequestDto matchIdRequestDto) {
        MatchResponseDto matchResponseDto = matchService.getMatchInfo(matchIdRequestDto.getMatchId());
        return ResponseEntity.ok(matchResponseDto);
    }
    @GetMapping("/day")
    public ResponseEntity<Integer> getNumberOfMatchesByDay(@RequestBody LocalDate date) {
        int numberOfMatches = matchService.getNumberOfMatchesByDay(date);
        return ResponseEntity.ok(numberOfMatches);
    }
    @GetMapping("/getCurrent")
    public ResponseEntity<Integer> getNumberOfCurrentMatches() {
        int currentMatches = matchService.getNumberOfCurrentMatches();
        return ResponseEntity.ok(currentMatches);
    }
    
    
}
