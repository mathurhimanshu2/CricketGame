package com.tekion.cricketgame.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MatchSummary {

    private int team1Runs;
    private int team1Wickets;

    private int team2Runs;
    private int team2Wicket;
    private String result;

}