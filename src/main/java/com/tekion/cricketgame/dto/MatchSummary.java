package com.tekion.cricketgame.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MatchSummary {

    private int Team1Runs;
    private int Team1Wickets;

    private int Team2Runs;
    private int Team2Wicket;
    private String Result;

    public MatchSummary(int team1Runs, int team1Wickets, int team2Runs, int team2Wicket, String result) {
        Team1Runs = team1Runs;
        Team1Wickets = team1Wickets;
        Team2Runs = team2Runs;
        Team2Wicket = team2Wicket;
        Result = result;
    }

    public MatchSummary() {

    }

    public int getTeam1Runs() {
        return Team1Runs;
    }

    public void setTeam1Runs(int team1Runs) {
        Team1Runs = team1Runs;
    }

    public int getTeam1Wickets() {
        return Team1Wickets;
    }

    public void setTeam1Wickets(int team1Wickets) {
        Team1Wickets = team1Wickets;
    }

    public int getTeam2Runs() {
        return Team2Runs;
    }

    public void setTeam2Runs(int team2Runs) {
        Team2Runs = team2Runs;
    }

    public int getTeam2Wicket() {
        return Team2Wicket;
    }

    public void setTeam2Wicket(int team2Wicket) {
        Team2Wicket = team2Wicket;
    }

    public String getResult() {
        return Result;
    }

    public void setResult(String result) {
        Result = result;
    }
}