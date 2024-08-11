package com.example.Dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GamePlayerResponseDto {
    private Long playerId;
    private String playerName;
    private LocalDateTime firstPlayed;
    private LocalDateTime duration;
}
