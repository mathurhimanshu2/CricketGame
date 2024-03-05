package com.tekion.cricketgame.utils;

import com.tekion.cricketgame.model.PlayerMatchStats;
import com.tekion.cricketgame.model.PlayerMatchStatsHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class PlayerMatchStatsListener extends AbstractMongoEventListener<PlayerMatchStats> {

    @Autowired
    private MongoTemplate mongoTemplate;
    @Override
    public void onAfterSave(AfterSaveEvent<PlayerMatchStats> event) {
        PlayerMatchStats playerMatchStats = event.getSource();
        PlayerMatchStats oldPlayerMatchStats = mongoTemplate.findById(playerMatchStats.getId(), PlayerMatchStats.class);

        PlayerMatchStatsHistory history = new PlayerMatchStatsHistory();
        history.setPlayerMatchStatsId(playerMatchStats.getId());
        history.setTimestamp(new Date());
        history.setUserId("user123");
        history.setOperation("UPDATE");

        history.setPreviousValue(oldPlayerMatchStats);
        history.setNewValue(playerMatchStats);

        mongoTemplate.save(history);
    }

    @Override
    public void onBeforeDelete(BeforeDeleteEvent<PlayerMatchStats> event) {
        String deletedPlayerMatchStatsId = event.getSource().get("_id").toString(); // Accessing the ID of the document being deleted
        PlayerMatchStats deletedPlayerMatchStats = mongoTemplate.findById(deletedPlayerMatchStatsId, PlayerMatchStats.class);

        PlayerMatchStatsHistory history = new PlayerMatchStatsHistory();
        history.setPlayerMatchStatsId(deletedPlayerMatchStatsId);
        history.setTimestamp(new Date());
        history.setUserId("user123");
        history.setOperation("DELETE");

        history.setPreviousValue(deletedPlayerMatchStats);

        mongoTemplate.save(history);
    }
}