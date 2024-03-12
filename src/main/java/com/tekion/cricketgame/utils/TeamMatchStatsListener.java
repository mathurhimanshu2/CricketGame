package com.tekion.cricketgame.utils;

import com.tekion.cricketgame.model.TeamMatchStats;
import com.tekion.cricketgame.model.TeamMatchStatsHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TeamMatchStatsListener extends AbstractMongoEventListener<TeamMatchStats> {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void onAfterSave(AfterSaveEvent<TeamMatchStats> event) {
        TeamMatchStats teamMatchStats = event.getSource();
        TeamMatchStatsHistory history = new TeamMatchStatsHistory();
        history.setTeamMatchStatsId(teamMatchStats.getId());
        history.setTimestamp(new Date());

        TeamMatchStats oldTeamMatchStats = mongoTemplate.findById(teamMatchStats.getId(), TeamMatchStats.class);
        history.setValue(oldTeamMatchStats);

        mongoTemplate.save(history);
    }

    @Override
    public void onBeforeDelete(BeforeDeleteEvent<TeamMatchStats> event) {
        String deletedTeamMatchStatsId = event.getSource().get("_id").toString();
        TeamMatchStats deletedTeamMatchStats = mongoTemplate.findById(deletedTeamMatchStatsId, TeamMatchStats.class);

        TeamMatchStatsHistory history = new TeamMatchStatsHistory();
        history.setTeamMatchStatsId(deletedTeamMatchStatsId);
        history.setTimestamp(new Date());

        history.setValue(deletedTeamMatchStats);

        mongoTemplate.save(history);
    }
}