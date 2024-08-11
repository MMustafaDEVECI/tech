package com.example.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "GamePlayer")
public class GamePlayer {
    @EmbeddedId
    private GamePlayerId id;

    private long winNumber;
    private long drawNumber;
    private long loseNumber;
    private String rank;
    final private LocalDateTime firstPlayed;
    private LocalDateTime timePlayed;

}