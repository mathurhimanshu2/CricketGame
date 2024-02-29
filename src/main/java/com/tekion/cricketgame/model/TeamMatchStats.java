package com.tekion.cricketgame.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "team_match_stats")
public class TeamMatchStats {
    @Id
    private String id;

    @Indexed(unique = true)
    private Long teamId;
    @Indexed(unique = true)
    private Long matchId;
    private int teamRuns;
    private int teamWickets;
    private int ballsFaced;

    public TeamMatchStats(Long teamId, Long matchId) {
        this.teamId = teamId;
        this.matchId = matchId;
        this.teamRuns = 0;
        this.teamWickets = 0;
        this.ballsFaced = 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getMatchId() {
        return matchId;
    }

    public void setMatchId(Long matchId) {
        this.matchId = matchId;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
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

    public int getBallsFaced() {
        return ballsFaced;
    }

    public void setBallsFaced(int ballsFaced) {
        this.ballsFaced = ballsFaced;
    }
    public void addRuns(int runs) {
        this.teamRuns += runs;
        this.ballsFaced++;
    }

    public void addWicket(){
        this.teamWickets++;
        this.ballsFaced++;
    }
}
