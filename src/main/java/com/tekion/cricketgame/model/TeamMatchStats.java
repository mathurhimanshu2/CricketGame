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
    public void addRuns(int runs) {
        this.teamRuns += runs;
        this.ballsFaced++;
    }

    public void addWicket(){
        this.teamWickets++;
        this.ballsFaced++;
    }
}