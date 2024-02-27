package com.tekion.cricketgame.service;

import com.tekion.cricketgame.model.Player;
import com.tekion.cricketgame.model.Team;
import com.tekion.cricketgame.repository.ITeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamService {

    @Autowired
    private ITeamRepository teamRepository;

    public Team savePlayer(Team team) {
        return teamRepository.save(team);
    }
}
