package com.tekion.cricketgame.repository;

import com.tekion.cricketgame.model.TeamMatchStats;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ITeamMatchStatsRepository extends MongoRepository<TeamMatchStats, String > {

    TeamMatchStats findByTeamIdAndMatchId(Long teamId,Long matchId);
}
