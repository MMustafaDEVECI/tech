package com.example.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "GamePlayer")
public class GamePlayer {
    @EmbeddedId
    private GamePlayerId id;

    private Long winNumber;
    private Long drawNumber;
    private Long loseNumber;
    private String ranks;
    private LocalDateTime firstPlayed; // final
    private LocalDateTime timePlayed;

}