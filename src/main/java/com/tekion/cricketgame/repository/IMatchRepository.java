package com.tekion.cricketgame.repository;

import com.tekion.cricketgame.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMatchRepository extends JpaRepository<Match,Long> {
}
