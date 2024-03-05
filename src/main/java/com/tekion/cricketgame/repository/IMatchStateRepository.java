package com.tekion.cricketgame.repository;

import com.tekion.cricketgame.model.MatchState;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IMatchStateRepository extends MongoRepository<MatchState, String> {

    MatchState findByMatchId(Long matchId);

}
