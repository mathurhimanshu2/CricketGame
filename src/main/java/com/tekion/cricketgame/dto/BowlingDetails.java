package com.tekion.cricketgame.dto;

import java.util.ArrayList;

public class BowlingDetails {

    private Long teamId;
    private ArrayList<PlayerBowlingStats> playerBallingList;

    public BowlingDetails(Long teamId, ArrayList<PlayerBowlingStats> playerBowlingStats) {
        this.teamId = teamId;
        this.playerBallingList = playerBowlingStats;
    }

    public BowlingDetails() {

    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public ArrayList<PlayerBowlingStats> getPlayerBallingList() {
        return playerBallingList;
    }

    public void setPlayerBallingList(ArrayList<PlayerBowlingStats> playerBallingList) {
        this.playerBallingList = playerBallingList;
    }
}