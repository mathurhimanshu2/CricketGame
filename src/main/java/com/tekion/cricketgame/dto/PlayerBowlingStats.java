package com.tekion.cricketgame.dto;

public class PlayerBowlingStats {

    private Long playerId;
    private int overs;
    private int wickets;

    public PlayerBowlingStats(Long playerId, int overs, int wickets) {
        this.playerId = playerId;
        this.overs = overs;
        this.wickets = wickets;
    }

    public PlayerBowlingStats() {

    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public int getOvers() {
        return overs;
    }

    public void setOvers(int overs) {
        this.overs = overs;
    }

    public int getWickets() {
        return wickets;
    }

    public void setWickets(int wickets) {
        this.wickets = wickets;
    }
}
