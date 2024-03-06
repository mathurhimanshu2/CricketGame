package com.tekion.cricketgame.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerBowlingStats {

    private Long playerId;
    private int overs;
    private int wickets;

}