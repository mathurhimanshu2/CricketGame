package com.tekion.cricketgame.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "player_stats")
public class PlayerStats {

    @Id
    private String id;
    @Indexed(unique = true)
    private Long playerId;
    private int runs;
    private int ballsFaced;
    private int wicketsTaken;

    public PlayerStats() {
    }

    public PlayerStats(Long playerId, int runs, int ballsFaced, int wicketsTaken) {
        this.playerId = playerId;
        this.runs = runs;
        this.ballsFaced = ballsFaced;
        this.wicketsTaken = wicketsTaken;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public int getRuns() {
        return runs;
    }

    public void setRuns(int runs) {
        this.runs = runs;
    }

    public int getBallsFaced() {
        return ballsFaced;
    }

    public void setBallsFaced(int ballsFaced) {
        this.ballsFaced = ballsFaced;
    }

    public int getWicketsTaken() {
        return wicketsTaken;
    }

    public void setWicketsTaken(int wicketsTaken) {
        this.wicketsTaken = wicketsTaken;
    }
}
