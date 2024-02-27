package com.tekion.cricketgame.repository;

import com.tekion.cricketgame.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITeamRepository extends JpaRepository<Team,Long> {
}
