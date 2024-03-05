package com.tekion.cricketgame.dto;

import java.util.ArrayList;
import java.util.HashMap;

public class BattingDetails {

    private Long teamId;
    private ArrayList<PlayerBattingStats> playerBattingList;

    public BattingDetails(){

    }

    public BattingDetails(Long teamId, ArrayList<PlayerBattingStats> playerBattingList) {
        this.teamId = teamId;
        this.playerBattingList = playerBattingList;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public ArrayList<PlayerBattingStats> getPlayerBattingList() {
        return playerBattingList;
    }

    public void setPlayerBattingList(ArrayList<PlayerBattingStats> playerBattingList) {
        this.playerBattingList = playerBattingList;
    }
}