package com.tekion.cricketgame.dto;

public class PlayerBallingStats {

    private String name;
    private int overs;
    private int wickets;

    public PlayerBallingStats(String name, int overs, int wickets) {
        this.name = name;
        this.overs = overs;
        this.wickets = wickets;
    }

    public PlayerBallingStats() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
