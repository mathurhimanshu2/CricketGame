package com.tekion.cricketgame.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "team_match_stats_history")
public class TeamMatchStatsHistory {
    @Id
    private String id;
    private String teamMatchStatsId;
    private Date timestamp;
    private String userId;
    private String operation;
    private Object Value;

}