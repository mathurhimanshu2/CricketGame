package com.tekion.cricketgame.dto;

import java.util.HashMap;

public class BattingDetails {

    private String team;
    private HashMap<String, PlayerBattingStats> playerBattingHashMap;

    public BattingDetails(String team, HashMap<String, PlayerBattingStats> playerBattingHashMap) {
        this.team = team;
        this.playerBattingHashMap = playerBattingHashMap;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public HashMap<String, PlayerBattingStats> getPlayerBattingHashMap() {
        return playerBattingHashMap;
    }

    public void setPlayerBattingHashMap(HashMap<String, PlayerBattingStats> playerBattingHashMap) {
        this.playerBattingHashMap = playerBattingHashMap;
    }
}
