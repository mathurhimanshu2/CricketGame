package com.tekion.cricketgame.controllers;

import com.tekion.cricketgame.model.Player;
import com.tekion.cricketgame.model.Team;
import com.tekion.cricketgame.service.PlayerService;
import com.tekion.cricketgame.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SampleController {


    @Autowired
    private PlayerService playerService;

    @Autowired
    private TeamService teamService;

    @PostMapping("/player")
    public String addPlayer(@RequestBody Player player){
        playerService.savePlayer(player);
        return "Success";
    }
    @PostMapping("/team")
    public String addTeam(@RequestBody Team team){
        teamService.savePlayer(team);
        return "Success";
    }
}
