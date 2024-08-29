package com.example.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "MatchPlayer")
public class MatchPlayer {
    @EmbeddedId
    private MatchPlayerId id;

    private Long killes;
    private Long death;
    private Long assist;
    private Long score;


}

