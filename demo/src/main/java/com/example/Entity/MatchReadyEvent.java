package com.example.Entity;

import java.util.List;

import org.springframework.context.ApplicationEvent;

public class MatchReadyEvent extends ApplicationEvent {
    private final Long gameId;
    private final List<Long> playerIds;

    public MatchReadyEvent(Object source, Long gameId, List<Long> playerIds) {
        super(source);
        this.gameId = gameId;
        this.playerIds = playerIds;
    }

    public Long getGameId() {
        return gameId;
    }

    public List<Long> getPlayerIds() {
        return playerIds;
    }
}
