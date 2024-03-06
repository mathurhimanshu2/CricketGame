package com.tekion.cricketgame.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BowlingDetails {

    private Long teamId;
    private List<PlayerBowlingStats> playerBallingList;

}