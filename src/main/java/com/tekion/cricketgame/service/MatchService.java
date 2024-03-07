package com.tekion.cricketgame.service;

import com.tekion.cricketgame.model.Match;
import com.tekion.cricketgame.model.Team;
import com.tekion.cricketgame.repository.IMatchRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MatchService {

    private final IMatchRepository matchRepository;

    private final TeamService teamService;


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
    public Match createMatch(MatchDTO matchDTO) {
        Team team1 = teamService.getTeamById(matchDTO.getTeam1Id());
        if (team1 == null) {
            throw new EntityNotFoundException("Team not found with id: " + matchDTO.getTeam1Id());
        }

        Team team2 = teamService.getTeamById(matchDTO.getTeam2Id());
        if (team2 == null) {
            throw new EntityNotFoundException("Team not found with id: " + matchDTO.getTeam2Id());
        }

        Match cricketMatch = new Match(matchDTO.getOvers(), team1, team2);
        return matchRepository.save(cricketMatch);
    }
}