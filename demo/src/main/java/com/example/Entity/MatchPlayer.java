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
@Table(name = "MatchPlayer")
public class MatchPlayer {
    @EmbeddedId
    private MatchPlayerId id;

    private long kill;
    private long death;
    private long assist;
    private long score;


}
@EqualsAndHashCode
@Embeddable
public class MatchlayerId implements Serializable {
    private Long matchId;
    private Long playerId;
}

