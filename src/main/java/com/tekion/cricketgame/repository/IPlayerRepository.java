package com.tekion.cricketgame.repository;

import com.tekion.cricketgame.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IPlayerRepository extends JpaRepository<Player,Long> {

}