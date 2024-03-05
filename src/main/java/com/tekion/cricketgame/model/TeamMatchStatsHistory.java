package com.tekion.cricketgame.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "team_match_stats_history")
public class TeamMatchStatsHistory {
    @Id
    private String id;
    private String teamMatchStatsId;
    private Date timestamp;
    private String userId;
    private String operation;
    private Object Value;

    public TeamMatchStatsHistory() {
    }

    public TeamMatchStatsHistory(String id, String teamMatchStatsId, Date timestamp, String userId, String operation, Object value) {
        this.id = id;
        this.teamMatchStatsId = teamMatchStatsId;
        this.timestamp = timestamp;
        this.userId = userId;
        this.operation = operation;
        Value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeamMatchStatsId() {
        return teamMatchStatsId;
    }

    public void setTeamMatchStatsId(String teamMatchStatsId) {
        this.teamMatchStatsId = teamMatchStatsId;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Object getValue() {
        return Value;
    }

    public void setValue(Object value) {
        Value = value;
    }
}
