package com.tekion.cricketgame.model;

import com.tekion.cricketgame.dto.InningSummary;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "scoreboard")
public class ScoreBoard {

    @Id
    private String id;

    @Indexed(unique = true)
    private Long matchId;

    private InningSummary firstInnings;

    private InningSummary secondInnings;


    public ScoreBoard() {
    }

    public ScoreBoard(Long matchId, InningSummary firstInnings, InningSummary secondInnings) {
        this.matchId = matchId;
        this.firstInnings = firstInnings;
        this.secondInnings = secondInnings;
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

    public InningSummary getFirstInnings() {
        return firstInnings;
    }

    public void setFirstInnings(InningSummary firstInnings) {
        this.firstInnings = firstInnings;
    }

    public InningSummary getSecondInnings() {
        return secondInnings;
    }

    public void setSecondInnings(InningSummary secondInnings) {
        this.secondInnings = secondInnings;
    }
}
