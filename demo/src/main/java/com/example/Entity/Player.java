package main.java.com.example.Entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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

    public long getPlayerId() {
        return playerId;
    }
    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }
    public String getNick() {
        return nick;
    }
    public void setNick(String nick) {
        this.nick = nick;
    }
    public long getLevel() {
        return level;
    }
    public void setLevel(long level) {
        this.level = level;
    }
    public long getXp() {
        return xp;
    }
    public void setXp(long xp) {
        this.xp = xp;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

}
