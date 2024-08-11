package com.example.Entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Game")
public class Game {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long gameId;
    private String gameName;
    private int teamSize;
    private int year;
    
}
