package com.tekion.cricketgame.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
@Document(collection = "player_match_stats_history")
public class PlayerMatchStatsHistory {
    @Id
    private String id;
    private String playerMatchStatsId;
    private Date timestamp;
    private String userId;
    private String operation;
    private Object previousValue;
    private Object newValue;

    public PlayerMatchStatsHistory(String id, String playerMatchStatsId, Date timestamp, String userId, String operation, Object previousValue, Object newValue) {
        this.id = id;
        this.playerMatchStatsId = playerMatchStatsId;
        this.timestamp = timestamp;
        this.userId = userId;
        this.operation = operation;
        this.previousValue = previousValue;
        this.newValue = newValue;
    }

    public PlayerMatchStatsHistory() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlayerMatchStatsId() {
        return playerMatchStatsId;
    }

    public void setPlayerMatchStatsId(String playerMatchStatsId) {
        this.playerMatchStatsId = playerMatchStatsId;
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

    public Object getPreviousValue() {
        return previousValue;
    }

    public void setPreviousValue(Object previousValue) {
        this.previousValue = previousValue;
    }

    public Object getNewValue() {
        return newValue;
    }

    public void setNewValue(Object newValue) {
        this.newValue = newValue;
    }
}
