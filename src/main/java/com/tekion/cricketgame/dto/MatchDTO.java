package com.tekion.cricketgame.dto;

import lombok.Data;

@Data
public class MatchDTO {
    private int overs;
    private Long team1Id;
    private Long team2Id;
}
