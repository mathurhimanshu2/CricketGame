package com.tekion.cricketgame.service;

import com.tekion.cricketgame.model.Player;
import com.tekion.cricketgame.model.Team;
import com.tekion.cricketgame.repository.IPlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    @Autowired
    private IPlayerRepository playerRepository;

    public Player savePlayer(Player player) {
        return playerRepository.save(player);
    }

    public List<Player> getTeamPlayers(Team team) {
        return playerRepository.findByTeam(team);
    }

    public Optional<Player> getPlayerById(Long id) {
        return playerRepository.findById(id);
    }

    public void deletePlayerById(Long id) {
        playerRepository.deleteById(id);
    }

    public Player updatePlayer(Long id, Player updatedPlayer,Team team) {
        Optional<Player> optionalPlayer = playerRepository.findById(id);
        if (optionalPlayer.isPresent()) {
            Player player = optionalPlayer.get();
            player.setPlayerName(updatedPlayer.getPlayerName());
            player.setTeam(team);
            return playerRepository.save(player);
        } else {
            throw new IllegalArgumentException("Player not found with id: " + id);
        }
    }
}
