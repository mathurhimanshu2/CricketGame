package com.tekion.cricketgame.repository;

import com.tekion.cricketgame.model.ScoreBoard;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IScoreBoardRepository extends MongoRepository<ScoreBoard,Long> {
}
