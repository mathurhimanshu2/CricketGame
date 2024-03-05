package com.tekion.cricketgame.service;

import com.tekion.cricketgame.model.PlayerMatchStats;
import com.tekion.cricketgame.repository.IPlayerMatchStatsRepository;
import com.tekion.cricketgame.repository.ITeamMatchStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerMatchStatsService {

    @Autowired
    IPlayerMatchStatsRepository playerMatchStatsRepository;

    public PlayerMatchStats findByPlayerIdAndMatchId(Long playerId, Long matchId){

        PlayerMatchStats playerMatchStats = playerMatchStatsRepository.findByPlayerIdAndMatchId(playerId,matchId);
        if (playerMatchStats!=null){
            return playerMatchStats;
        } else {
            playerMatchStats = new PlayerMatchStats(playerId,matchId);
            playerMatchStatsRepository.save(playerMatchStats);
            return playerMatchStats;
        }
    }


    public void addBatsmanRuns(Long playerId,Long matchId, int runsToAdd){
        PlayerMatchStats playerMatchStats = playerMatchStatsRepository.findByPlayerIdAndMatchId(playerId,matchId);
        if (playerMatchStats!=null){
            int currentRuns = playerMatchStats.getRuns();
            int currentBallsFaced = playerMatchStats.getBallsFaced();
            playerMatchStats.setRuns(currentRuns+runsToAdd);
            playerMatchStats.setBallsFaced(currentBallsFaced+1);

            playerMatchStatsRepository.save(playerMatchStats);
        } else{
            playerMatchStats = new PlayerMatchStats(playerId,matchId);
            playerMatchStats.setRuns(runsToAdd);
            playerMatchStats.setBallsFaced(1);
            playerMatchStatsRepository.save(playerMatchStats);
        }
    }

    public void addBowlerRuns(Long playerId,Long matchId, int runsToAdd){
        PlayerMatchStats playerMatchStats = playerMatchStatsRepository.findByPlayerIdAndMatchId(playerId,matchId);
        if (playerMatchStats!=null){
            int currentRunsConceded = playerMatchStats.getRunsConceded();
            int currentBallsBowled = playerMatchStats.getBallsBowled();
            playerMatchStats.setRunsConceded(currentRunsConceded+runsToAdd);
            playerMatchStats.setBallsBowled(currentBallsBowled+1);

            playerMatchStatsRepository.save(playerMatchStats);
        } else{
            playerMatchStats = new PlayerMatchStats(playerId, matchId);
            playerMatchStatsRepository.save(playerMatchStats);
            playerMatchStats.setRunsConceded(runsToAdd);
            playerMatchStats.setBallsBowled(1);
        }
    }

    public void addWicket(Long playerId,Long matchId){
        PlayerMatchStats playerMatchStats = playerMatchStatsRepository.findByPlayerIdAndMatchId(playerId,matchId);
        if (playerMatchStats!=null){
            int currentWickets = playerMatchStats.getWicketsTaken();
            int currentBallsBowled = playerMatchStats.getBallsBowled();
            playerMatchStats.setWicketsTaken(currentWickets+1);
            playerMatchStats.setBallsBowled(currentBallsBowled+1);

            playerMatchStatsRepository.save(playerMatchStats);
        } else{
            playerMatchStats = new PlayerMatchStats(playerId, matchId);
            playerMatchStatsRepository.save(playerMatchStats);
            playerMatchStats.setWicketsTaken(1);
            playerMatchStats.setBallsBowled(1);
        }
    }
}