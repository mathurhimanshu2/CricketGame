package com.tekion.cricketgame.service;

import com.tekion.cricketgame.model.MatchState;
import com.tekion.cricketgame.repository.IMatchStateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MatchStateService {

    private final IMatchStateRepository matchStateRepository;

    public MatchState getMatchStateByMatchId(Long matchId){
        MatchState matchState = matchStateRepository.findByMatchId(matchId);
        if (matchState != null) {
            return matchState;
        } else {
            return initializeMatchState(matchId);
        }
    }
    @Transactional
    public MatchState initializeMatchState(Long matchId) {
        MatchState matchState = new MatchState();
        matchState.setMatchId(matchId);
        matchState.setCurrentInnings("first");
        return matchStateRepository.save(matchState);
    }

    public MatchState save(MatchState matchState){
        return matchStateRepository.save(matchState);
    }
}