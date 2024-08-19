package com.example.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameResponseDto {
    private Long gameId;
    private String name;
    private int teamSize;
    private int year;
}
