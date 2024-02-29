package com.tekion.cricketgame.service;

import com.tekion.cricketgame.dto.InningSummary;
import com.tekion.cricketgame.model.Match;
import com.tekion.cricketgame.model.ScoreBoard;
import com.tekion.cricketgame.model.TeamMatchStats;
import com.tekion.cricketgame.repository.IPlayerMatchStatsRepository;
import com.tekion.cricketgame.repository.IScoreBoardRepository;
import com.tekion.cricketgame.repository.ITeamMatchStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class ScoreCardService {

    @Autowired
    IScoreBoardRepository scoreBoardRepository;

    public void fillScoreCard(InningSummary firstInnings,InningSummary secondInnings,Match match){
        ScoreBoard scoreBoard = scoreBoardRepository.findByMatchId(match.getMatchId());
        if (scoreBoard!=null){
            scoreBoard.setFirstInnings(firstInnings);
            scoreBoard.setSecondInnings(secondInnings);
            scoreBoardRepository.save(scoreBoard);
        } else {
            scoreBoard = new ScoreBoard();
            scoreBoard.setFirstInnings(firstInnings);
            scoreBoard.setSecondInnings(secondInnings);
            scoreBoardRepository.save(scoreBoard);

        }

    }
}
