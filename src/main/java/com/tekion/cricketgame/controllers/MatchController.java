package com.tekion.cricketgame.controllers;

import com.tekion.cricketgame.dto.MatchSummary;
import com.tekion.cricketgame.model.Match;
import com.tekion.cricketgame.model.MatchState;
import com.tekion.cricketgame.service.MatchService;
import com.tekion.cricketgame.service.MatchStateService;
import com.tekion.cricketgame.utils.Helper;
import com.tekion.cricketgame.utils.exceptions.MatchNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/match")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @Autowired
    private MatchStateService matchStateService;

    @Autowired
    private Helper helper;

    @GetMapping
    public ResponseEntity<List<Match>> getAllMatches() {
        List<Match> matches = matchService.getAllMatches();
        return new ResponseEntity<>(matches, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Match> getMatchById(@PathVariable Long id) {
        Match match = matchService.getMatchById(id);
        if (match != null) {
            return new ResponseEntity<>(match, HttpStatus.OK);
        } else {
            throw new MatchNotFoundException("Match not found with id: " + id);
        }
    }

    @PostMapping
    public ResponseEntity<Match> createMatch(@RequestBody Match match) {
        Match savedMatch = matchService.createMatch(match);
        return new ResponseEntity<>(savedMatch, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Match> updateMatch(@PathVariable Long id, @RequestBody Match updatedMatch) {
        Match match = matchService.updateMatch(id, updatedMatch);
        if (match != null) {
            return new ResponseEntity<>(match, HttpStatus.OK);
        } else {
            throw new MatchNotFoundException("Match not found with id: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMatch(@PathVariable Long id) {
        matchService.deleteMatch(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/simulate/{matchId}/{ballsToBeSimulated}")
    public ResponseEntity<MatchSummary> simulateMatch(@PathVariable Long matchId, @PathVariable int ballsToBeSimulated){

        Match match = matchService.getMatchById(matchId);
        if (match != null) {

            MatchState matchState = matchStateService.getMatchStateByMatchId(matchId);
            MatchSummary matchSummary = helper.simulateMatch(match,ballsToBeSimulated,matchState);
            return new ResponseEntity<>(matchSummary, HttpStatus.OK);
        } else {
            throw new MatchNotFoundException("Match not found with id: " + matchId);
        }

    }

}