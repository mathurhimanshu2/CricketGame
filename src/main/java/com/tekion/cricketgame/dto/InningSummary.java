package com.tekion.cricketgame.dto;

public class InningSummary {

    private BattingDetails battingDetails;
    private BallingDetails ballingDetails;

    public InningSummary(BattingDetails battingDetails, BallingDetails ballingDetails) {
        this.battingDetails = battingDetails;
        this.ballingDetails = ballingDetails;
    }

    public BattingDetails getBattingDetails() {
        return battingDetails;
    }

    public void setBattingDetails(BattingDetails battingDetails) {
        this.battingDetails = battingDetails;
    }

    public BallingDetails getBallingDetails() {
        return ballingDetails;
    }

    public void setBallingDetails(BallingDetails ballingDetails) {
        this.ballingDetails = ballingDetails;
    }
}
