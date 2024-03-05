package com.tekion.cricketgame.dto;

public class InningSummary {

    private BattingDetails battingDetails;
    private BowlingDetails bowlingDetails;

    private int teamRuns;
    private int teamWickets;

    public InningSummary(){

    }


    public InningSummary(BattingDetails battingDetails, BowlingDetails bowlingDetails, int teamRuns, int teamWickets) {
        this.battingDetails = battingDetails;
        this.bowlingDetails = bowlingDetails;
        this.teamRuns = teamRuns;
        this.teamWickets = teamWickets;
    }

    public BattingDetails getBattingDetails() {
        return battingDetails;
    }

    public void setBattingDetails(BattingDetails battingDetails) {
        this.battingDetails = battingDetails;
    }

    public BowlingDetails getBowlingDetails() {
        return bowlingDetails;
    }

    public void setBowlingDetails(BowlingDetails bowlingDetails) {
        this.bowlingDetails = bowlingDetails;
    }

    public int getTeamRuns() {
        return teamRuns;
    }

    public void setTeamRuns(int teamRuns) {
        this.teamRuns = teamRuns;
    }

    public int getTeamWickets() {
        return teamWickets;
    }

    public void setTeamWickets(int teamWickets) {
        this.teamWickets = teamWickets;
    }
}