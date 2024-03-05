package com.tekion.cricketgame.dto;

public class PlayerBattingStats {
    private Long playerId;
    private int runs;
    private int balls;

    public PlayerBattingStats() {

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

    public int getBalls() {
        return balls;
    }

    public void setBalls(int balls) {
        this.balls = balls;
    }

    public void addRuns(int runs) {
        this.runs += runs;
        this.balls++;
    }
}