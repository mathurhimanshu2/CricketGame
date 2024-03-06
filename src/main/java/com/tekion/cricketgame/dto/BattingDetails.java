package com.tekion.cricketgame.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BattingDetails {

    private Long teamId;
    private List<PlayerBattingStats> playerBattingList;

}