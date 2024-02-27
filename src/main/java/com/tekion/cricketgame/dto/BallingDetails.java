package com.tekion.cricketgame.dto;

import java.util.ArrayList;

public class BallingDetails {

    private String team;
    private ArrayList<PlayerBallingStats> playerBallingStats;

    public BallingDetails(String team, ArrayList<PlayerBallingStats> playerBallingStats) {
        this.team = team;
        this.playerBallingStats = playerBallingStats;
    }

    public BallingDetails() {

    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public ArrayList<PlayerBallingStats> getPlayerBowlings() {
        return playerBallingStats;
    }

    public void setPlayerBowlings(ArrayList<PlayerBallingStats> playerBallingStats) {
        this.playerBallingStats = playerBallingStats;
    }
}
