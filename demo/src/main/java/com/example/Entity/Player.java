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
@Table(name = "Player")
public class Player {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long playerId;
    private String nick;
    private long level;
    private long xp;
    private String country;

}
