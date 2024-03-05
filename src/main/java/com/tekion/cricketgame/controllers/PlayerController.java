package com.tekion.cricketgame.controllers;

import com.tekion.cricketgame.model.Player;
import com.tekion.cricketgame.service.PlayerService;
import com.tekion.cricketgame.service.TeamService;
import com.tekion.cricketgame.utils.exceptions.PlayerCreationException;
import com.tekion.cricketgame.utils.exceptions.PlayerNotFoundException;
import com.tekion.cricketgame.utils.exceptions.PlayerUpdateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private TeamService teamService;

    @GetMapping
    public ResponseEntity<List<Player>> getAllPlayers() {
        List<Player> players = playerService.getAllPlayers();
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable Long id) {
        Player player = playerService.getPlayerById(id);
        if (player != null) {
            return new ResponseEntity<>(player, HttpStatus.OK);
        } else {
            throw new PlayerNotFoundException("Player not found with id: " + id);
        }
    }


    @PostMapping("/{teamId}")
    public ResponseEntity<Player> addPlayerToTeam(@PathVariable Long teamId, @RequestBody Player player) {
        if (teamService.getTeamById(teamId)!=null) {
            Player savedPlayer = playerService.addPlayerToTeam(teamId, player);
            return new ResponseEntity<>(savedPlayer, HttpStatus.CREATED);
        } else {
            throw new PlayerCreationException("Invalid player data provided.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Player> updatePlayer(@PathVariable Long id, @RequestBody Player updatedPlayer) {
        Player player = playerService.updatePlayer(id, updatedPlayer);
        if (player != null) {
            return new ResponseEntity<>(player, HttpStatus.OK);
        } else {
            throw new PlayerUpdateException("Failed to update player with id: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable Long id) {
        playerService.deletePlayerById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}