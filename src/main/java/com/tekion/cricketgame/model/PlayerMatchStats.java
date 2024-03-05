package com.tekion.cricketgame.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "player_match_stats")
public class PlayerMatchStats {

    @Id
    private String id;
    @Indexed(unique = true)
    private Long playerId;
    @Indexed(unique = true)
    private Long matchId;
    private int runs;
    private int ballsFaced;
    private int wicketsTaken;

    private int ballsBowled;
    private int runsConceded;

    private boolean notOut;

    public PlayerMatchStats() {
    }
    public PlayerMatchStats(Long playerId, Long matchId) {
        this.playerId = playerId;
        this.matchId = matchId;
        this.runs = 0;
        this.ballsFaced = 0;
        this.wicketsTaken = 0;
        this.ballsBowled = 0;
        this.runsConceded = 0;
        this.notOut = true;
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

    public Long getMatchId() {
        return matchId;
    }

    public void setMatchId(Long matchId) {
        this.matchId = matchId;
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

    public boolean isNotOut() {
        return notOut;
    }

    public void setNotOut(boolean notOut) {
        this.notOut = notOut;
    }

    public int getBallsBowled() {
        return ballsBowled;
    }

    public void setBallsBowled(int ballsBowled) {
        this.ballsBowled = ballsBowled;
    }

    public int getRunsConceded() {
        return runsConceded;
    }

    public void setRunsConceded(int runsConceded) {
        this.runsConceded = runsConceded;
    }

    public void addBatsmanRuns(int runs) {
        this.runs += runs;
        this.ballsFaced++;
    }

    public void addBowlerRuns(int runs) {
        this.runsConceded+=runs;
        this.ballsBowled++;
    }

    public void addWicket(){
        this.wicketsTaken++;
        this.ballsBowled++;
    }



}