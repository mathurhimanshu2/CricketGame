package com.tekion.cricketgame.service;

import com.tekion.cricketgame.model.Match;
import com.tekion.cricketgame.model.Team;
import com.tekion.cricketgame.repository.IMatchRepository;
import com.tekion.cricketgame.repository.ITeamRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MatchService {

    @Autowired
    private IMatchRepository matchRepository;

    @Autowired
    private ITeamRepository teamRepository;

    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }

    public Match getMatchById(Long id) {
        Optional<Match> matchOptional = matchRepository.findById(id);
        return matchOptional.orElse(null);
    }

    public Match saveMatch(Match match) {
        return matchRepository.save(match);
    }

    public void deleteMatch(Long id) {
        matchRepository.deleteById(id);
    }

    public Match updateMatch(Long id, Match updatedMatch) {
        Optional<Match> matchOptional = matchRepository.findById(id);
        if (matchOptional.isPresent()) {
            Match match = matchOptional.get();
            match.setOvers(updatedMatch.getOvers());
            match.setTeam1(updatedMatch.getTeam1());
            match.setTeam2(updatedMatch.getTeam2());
            return matchRepository.save(match);
        }
        return null;
    }

    @Transactional
    public Match createMatch(Match match) {
        Team team1 = teamRepository.findById(match.getTeam1().getTeamId())
                .orElseThrow(() -> new EntityNotFoundException("Team not found with id: " + match.getTeam1().getTeamId()));
        Team team2 = teamRepository.findById(match.getTeam2().getTeamId())
                .orElseThrow(() -> new EntityNotFoundException("Team not found with id: " + match.getTeam2().getTeamId()));

        Match cricketMatch = new Match(match.getOvers(), team1, team2);

        // Save the match entity
        return matchRepository.save(cricketMatch);
    }
}