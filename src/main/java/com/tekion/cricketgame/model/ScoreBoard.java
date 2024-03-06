package com.tekion.cricketgame.model;

import com.tekion.cricketgame.dto.InningSummary;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@NoArgsConstructor
@Document(collection = "scoreboard")
public class ScoreBoard {

    @Id
    private String id;

    @Indexed(unique = true)
    private Long matchId;

    private InningSummary firstInnings;

    private InningSummary secondInnings;

    public ScoreBoard(Long matchId, InningSummary firstInnings, InningSummary secondInnings) {
        this.matchId = matchId;
        this.firstInnings = firstInnings;
        this.secondInnings = secondInnings;
    }
}