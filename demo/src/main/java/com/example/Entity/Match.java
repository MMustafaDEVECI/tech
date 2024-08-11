package com.example.Entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.*;;


@Data
@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Match")
public class Match {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long matchId;
    @Column(name = "game_id", nullable = false)
    private Long gameId;
    private LocalDateTime matchTime;
    private long duration;

}
