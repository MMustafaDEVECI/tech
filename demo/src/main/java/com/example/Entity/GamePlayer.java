package com.example.Entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "GamePlayer")
public class GamePlayer {
    @EmbeddedId
    private GamePlayerId id;

    private long winNumber;
    private long drawNumber;
    private long loseNumber;
    final private LocalDateTime firstPlayed;
    private LocalDateTime timePlayed;


}
@EqualsAndHashCode
@Embeddable
public class GamePlayerId implements Serializable {
    private Long gameId;
    private Long playerId;
}
