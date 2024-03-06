package com.tekion.cricketgame.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
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
    private String matchStatus;

    private boolean firstInningsOver;

    private int currentFirstInningBatsman;

    private int currentFirstInningBowler;

    private int currentSecondInningBatsman;

    private int currentSecondInningBowler;

}