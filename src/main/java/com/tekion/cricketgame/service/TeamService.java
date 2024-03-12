package com.tekion.cricketgame.service;

import com.tekion.cricketgame.model.Player;
import com.tekion.cricketgame.model.Team;
import com.tekion.cricketgame.repository.ITeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeamService {
    private final ITeamRepository teamRepository;

    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    public Team getTeamById(Long id) {
        Optional<Team> teamOptional = teamRepository.findById(id);
        return teamOptional.orElse(null);
    }

    public Team saveTeam(Team team) {
        return teamRepository.save(team);
    }

    public void deleteTeam(Long id) {
        teamRepository.deleteById(id);
    }

    public Team updateTeam(Long id, Team updatedTeam) {
        Optional<Team> teamOptional = teamRepository.findById(id);
        if (teamOptional.isPresent()) {
            Team team = teamOptional.get();
            team.setTeamName(updatedTeam.getTeamName());
            return teamRepository.save(team);
        }
        return null;
    }
}