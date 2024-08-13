package com.example.Entity;

import java.util.List;

import org.springframework.context.ApplicationEvent;

public class MatchEndedEvent extends ApplicationEvent {
    private final Long matchId;
    private final List<Long> playerIds;

    public MatchEndedEvent(Object source, Long matchId, List<Long> playerIds) {
        super(source);
        this.matchId = matchId;
        this.playerIds = playerIds;
    }

    public Long getMatchId() {
        return matchId;
    }

    public List<Long> getPlayerIds() {
        return playerIds;
    }
}