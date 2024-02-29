package com.tekion.cricketgame.repository;

import com.tekion.cricketgame.model.PlayerMatchStats;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPlayerMatchStatsRepository extends MongoRepository<PlayerMatchStats, String> {

    PlayerMatchStats findByPlayerIdAndMatchId(Long playerId,Long matchId);
}
