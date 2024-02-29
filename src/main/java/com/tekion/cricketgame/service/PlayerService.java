package com.tekion.cricketgame.service;

import com.tekion.cricketgame.model.Player;
import com.tekion.cricketgame.model.Team;
import com.tekion.cricketgame.repository.IPlayerRepository;
import com.tekion.cricketgame.repository.ITeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    @Autowired
    private IPlayerRepository playerRepository;

    @Autowired
    private ITeamRepository teamRepository;

    public Player savePlayer(Player player) {
        return playerRepository.save(player);
    }

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }
    public Player getPlayerById(Long id) {
        Optional<Player> playerOptional = playerRepository.findById(id);
        return playerOptional.orElse(null);
    }
    public void deletePlayerById(Long id) {
        playerRepository.deleteById(id);
    }

    public Player updatePlayer(Long id, Player updatedPlayer) {
        Optional<Player> playerOptional = playerRepository.findById(id);
        if (playerOptional.isPresent()) {
            Player player = playerOptional.get();
            player.setPlayerName(updatedPlayer.getPlayerName());
            player.setTeam(updatedPlayer.getTeam());
            return playerRepository.save(player);
        } else {
            return null;
        }
    }

    @Transactional
    public Player addPlayerToTeam(Long teamId, Player player) {
        // Fetch the Team entity from the database
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new IllegalArgumentException("Team not found with id: " + teamId));

        player.setTeam(team);

        return playerRepository.save(player);
    }

}
