package com.tekion.cricketgame.model;

import jakarta.persistence.*;
@Entity
@Table(name = "cricket-match")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "match_id")
    private Long matchId;

    @Column(name = "overs")
    private int overs;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "team1_id")
    private Team team1;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "team2_id")
    private Team team2;

    public Match() {
    }

    public Match(int overs, Team team1, Team team2) {
        this.overs = overs;
        this.team1 = team1;
        this.team2 = team2;
    }

    public Long getMatchId() {
        return matchId;
    }

    public void setMatchId(Long matchId) {
        this.matchId = matchId;
    }

    public int getOvers() {
        return overs;
    }

    public void setOvers(int overs) {
        this.overs = overs;
    }

    public Team getTeam1() {
        return team1;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }
}