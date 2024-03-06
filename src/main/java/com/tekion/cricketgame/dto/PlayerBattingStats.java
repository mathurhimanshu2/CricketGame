package com.tekion.cricketgame.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerBattingStats {
    private Long playerId;
    private int runs;
    private int balls;

    public void addRuns(int runs) {
        this.runs += runs;
        this.balls++;
    }
}