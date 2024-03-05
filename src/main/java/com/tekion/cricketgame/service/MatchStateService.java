package com.tekion.cricketgame.service;

import com.tekion.cricketgame.model.MatchState;
import com.tekion.cricketgame.repository.IMatchStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatchStateService {

    @Autowired
    private IMatchStateRepository matchStateRepository;

    public MatchState getMatchStateByMatchId(Long matchId){
        MatchState matchState = matchStateRepository.findByMatchId(matchId);
        if (matchState!=null){
            return matchState;
        } else {
            matchState = new MatchState();
            matchState.setMatchId(matchId);
            matchState.setCurrentInnings("first");
            matchStateRepository.save(matchState);
            return matchState;
        }
    }

    public MatchState save(MatchState matchState){
        return matchStateRepository.save(matchState);
    }
}