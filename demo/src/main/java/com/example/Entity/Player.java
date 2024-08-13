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
    private Long playerId;
    private String nick;
    private Long level;
    private Long xp;
    private String country;

}
