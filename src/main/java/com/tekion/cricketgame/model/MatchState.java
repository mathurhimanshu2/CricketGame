package com.tekion.cricketgame.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "match_state")
public class MatchState {

    @Id
    private String id;

    @Indexed(unique = true)
    private Long matchId;

    private int runsMadeInFirstInnings;
    private int runsMadeInSecondInnings;

    private int wicketsTakenInFirstInnings;

    private int wicketsTakenInSecondInnings;

    private int ballsPlayedInFirstInnings;

    private int ballsPlayedInSecondInnings;
    private String currentInnings;
    private boolean matchOver;

    private boolean firstInningsOver;

    private int currentFirstInningBatsman;

    private int currentFirstInningBowler;

    private int currentSecondInningBatsman;

    private int currentSecondInningBowler;

    public MatchState() {

    }

    public MatchState(String id, Long matchId, int runsMadeInFirstInnings, int runsMadeInSecondInnings, int wicketsTakenInFirstInnings, int wicketsTakenInSecondInnings, int ballsPlayedInFirstInnings, int ballsPlayedInSecondInnings, String currentInnings, int currentFirstInningBatsman, int currentFirstInningBowler, int currentSecondInningBatsman, int currentSecondInningBowler) {
        this.id = id;
        this.matchId = matchId;
        this.runsMadeInFirstInnings = runsMadeInFirstInnings;
        this.runsMadeInSecondInnings = runsMadeInSecondInnings;
        this.wicketsTakenInFirstInnings = wicketsTakenInFirstInnings;
        this.wicketsTakenInSecondInnings = wicketsTakenInSecondInnings;
        this.ballsPlayedInFirstInnings = ballsPlayedInFirstInnings;
        this.ballsPlayedInSecondInnings = ballsPlayedInSecondInnings;
        this.currentInnings = currentInnings;
        this.currentFirstInningBatsman = currentFirstInningBatsman;
        this.currentFirstInningBowler = currentFirstInningBowler;
        this.currentSecondInningBatsman = currentSecondInningBatsman;
        this.currentSecondInningBowler = currentSecondInningBowler;
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

    public String getCurrentInnings() {
        return currentInnings;
    }

    public void setCurrentInnings(String currentInnings) {
        this.currentInnings = currentInnings;
    }

    public int getCurrentFirstInningBatsman() {
        return currentFirstInningBatsman;
    }

    public void setCurrentFirstInningBatsman(int currentFirstInningBatsman) {
        this.currentFirstInningBatsman = currentFirstInningBatsman;
    }

    public int getCurrentFirstInningBowler() {
        return currentFirstInningBowler;
    }

    public void setCurrentFirstInningBowler(int currentFirstInningBowler) {
        this.currentFirstInningBowler = currentFirstInningBowler;
    }

    public int getBallsPlayedInFirstInnings() {
        return ballsPlayedInFirstInnings;
    }

    public void setBallsPlayedInFirstInnings(int ballsPlayedInFirstInnings) {
        this.ballsPlayedInFirstInnings = ballsPlayedInFirstInnings;
    }

    public int getBallsPlayedInSecondInnings() {
        return ballsPlayedInSecondInnings;
    }

    public void setBallsPlayedInSecondInnings(int ballsPlayedInSecondInnings) {
        this.ballsPlayedInSecondInnings = ballsPlayedInSecondInnings;
    }

    public int getCurrentSecondInningBatsman() {
        return currentSecondInningBatsman;
    }

    public void setCurrentSecondInningBatsman(int currentSecondInningBatsman) {
        this.currentSecondInningBatsman = currentSecondInningBatsman;
    }

    public int getCurrentSecondInningBowler() {
        return currentSecondInningBowler;
    }

    public void setCurrentSecondInningBowler(int currentSecondInningBowler) {
        this.currentSecondInningBowler = currentSecondInningBowler;
    }

    public int getRunsMadeInFirstInnings() {
        return runsMadeInFirstInnings;
    }

    public void setRunsMadeInFirstInnings(int runsMadeInFirstInnings) {
        this.runsMadeInFirstInnings = runsMadeInFirstInnings;
    }

    public int getRunsMadeInSecondInnings() {
        return runsMadeInSecondInnings;
    }

    public void setRunsMadeInSecondInnings(int runsMadeInSecondInnings) {
        this.runsMadeInSecondInnings = runsMadeInSecondInnings;
    }

    public int getWicketsTakenInFirstInnings() {
        return wicketsTakenInFirstInnings;
    }

    public void setWicketsTakenInFirstInnings(int wicketsTakenInFirstInnings) {
        this.wicketsTakenInFirstInnings = wicketsTakenInFirstInnings;
    }

    public int getWicketsTakenInSecondInnings() {
        return wicketsTakenInSecondInnings;
    }

    public void setWicketsTakenInSecondInnings(int wicketsTakenInSecondInnings) {
        this.wicketsTakenInSecondInnings = wicketsTakenInSecondInnings;
    }

    public boolean isMatchOver() {
        return matchOver;
    }

    public void setMatchOver(boolean matchOver) {
        this.matchOver = matchOver;
    }

    public boolean isFirstInningsOver() {
        return firstInningsOver;
    }

    public void setFirstInningsOver(boolean firstInningsOver) {
        this.firstInningsOver = firstInningsOver;
    }
}
