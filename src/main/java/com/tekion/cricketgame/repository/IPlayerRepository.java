package com.tekion.cricketgame.repository;

import com.tekion.cricketgame.model.Player;
import com.tekion.cricketgame.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPlayerRepository extends JpaRepository<Player,Long> {

    List<Player> findByTeam(Team team);
}
