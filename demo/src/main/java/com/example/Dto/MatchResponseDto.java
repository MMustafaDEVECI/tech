package com.example.Dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchResponseDto {
    private long matchId;
    private LocalDateTime matchTime;
    private long duration;
    private long gameId;
}