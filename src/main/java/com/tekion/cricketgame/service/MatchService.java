package com.tekion.cricketgame.service;

import com.tekion.cricketgame.model.Match;
import com.tekion.cricketgame.repository.IMatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatchService {

    @Autowired
    private IMatchRepository matchRepository;


}
