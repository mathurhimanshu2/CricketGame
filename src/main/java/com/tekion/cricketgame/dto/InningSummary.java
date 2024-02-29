package com.tekion.cricketgame.dto;

public class InningSummary {

    private BattingDetails battingDetails;
    private BowlingDetails bowlingDetails;

    public InningSummary(){

    }

    public InningSummary(BattingDetails battingDetails, BowlingDetails bowlingDetails) {
        this.battingDetails = battingDetails;
        this.bowlingDetails = bowlingDetails;
    }

    public BattingDetails getBattingDetails() {
        return battingDetails;
    }

    public void setBattingDetails(BattingDetails battingDetails) {
        this.battingDetails = battingDetails;
    }

    public BowlingDetails getBallingDetails() {
        return bowlingDetails;
    }

    public void setBallingDetails(BowlingDetails bowlingDetails) {
        this.bowlingDetails = bowlingDetails;
    }
}
