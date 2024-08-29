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
@Table(name = "`Match`")
public class Match {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long matchId;
    @Column(name = "game_id")
    private Long gameId;
    @Column(name = "match_time")
    private LocalDateTime matchTime;
    private Long duration;

}
