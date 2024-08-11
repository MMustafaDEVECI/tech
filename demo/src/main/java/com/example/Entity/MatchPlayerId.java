package com.example.Entity;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@Embeddable
public class MatchPlayerId implements Serializable {
    private Long matchId;
    private Long playerId;
}
