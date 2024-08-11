package com.example.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor

@Table(name = "MatchPlayer")
public class MatchPlayer {
    @EmbeddedId
    private MatchPlayerId id;

    private long kill;
    private long death;
    private long assist;
    private long score;


}

