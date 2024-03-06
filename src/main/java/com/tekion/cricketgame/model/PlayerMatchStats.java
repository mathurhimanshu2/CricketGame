package com.tekion.cricketgame.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "player_match_stats")
public class PlayerMatchStats {

    @Id
    private String id;
    @Indexed(unique = true)
    private Long playerId;
    @Indexed(unique = true)
    private Long matchId;
    private int runs;
    private int ballsFaced;
    private int wicketsTaken;

    private int ballsBowled;
    private int runsConceded;

    private boolean notOut;

    public PlayerMatchStats(Long playerId, Long matchId) {
        this.playerId = playerId;
        this.matchId = matchId;
        this.runs = 0;
        this.ballsFaced = 0;
        this.wicketsTaken = 0;
        this.ballsBowled = 0;
        this.runsConceded = 0;
        this.notOut = true;
    }

    public void addBatsmanRuns(int runs) {
        this.runs += runs;
        this.ballsFaced++;
    }

    public void addBowlerRuns(int runs) {
        this.runsConceded+=runs;
        this.ballsBowled++;
    }

    public void addWicket(){
        this.wicketsTaken++;
        this.ballsBowled++;
    }



}