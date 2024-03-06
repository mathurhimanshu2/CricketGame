package com.tekion.cricketgame.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InningSummary {

    private BattingDetails battingDetails;
    private BowlingDetails bowlingDetails;

    private int teamRuns;
    private int teamWickets;

}