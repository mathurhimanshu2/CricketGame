package com.tekion.cricketgame.service;

import com.tekion.cricketgame.model.PlayerMatchStats;
import com.tekion.cricketgame.repository.IPlayerMatchStatsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class PlayerMatchStatsService {

    private final IPlayerMatchStatsRepository playerMatchStatsRepository;

    public PlayerMatchStats findByPlayerIdAndMatchId(Long playerId, Long matchId){

        PlayerMatchStats playerMatchStats = playerMatchStatsRepository.findByPlayerIdAndMatchId(playerId, matchId);
        if (playerMatchStats != null) {
            return playerMatchStats;
        } else {
           return initializePlayerMatchStats(playerId, matchId);
        }
    }

    @Transactional
    public PlayerMatchStats initializePlayerMatchStats(Long playerId, Long matchId) {
        PlayerMatchStats playerMatchStats = new PlayerMatchStats(playerId, matchId);
        return playerMatchStatsRepository.save(playerMatchStats);
    }


    public void upsertBatsmanRuns(Long playerId,Long matchId, int runsToAdd){
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

    public void upsertBowlerRuns(Long playerId,Long matchId, int runsToAdd){
        PlayerMatchStats playerMatchStats = playerMatchStatsRepository.findByPlayerIdAndMatchId(playerId,matchId);
        if (playerMatchStats!=null){
            int currentRunsConceded = playerMatchStats.getRunsConceded();
            int currentBallsBowled = playerMatchStats.getBallsBowled();
            playerMatchStats.setRunsConceded(currentRunsConceded+runsToAdd);
            playerMatchStats.setBallsBowled(currentBallsBowled+1);

            playerMatchStatsRepository.save(playerMatchStats);
        } else{
            playerMatchStats = new PlayerMatchStats(playerId, matchId);
            playerMatchStats.setRunsConceded(runsToAdd);
            playerMatchStats.setBallsBowled(1);
            playerMatchStatsRepository.save(playerMatchStats);
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
            playerMatchStats.setWicketsTaken(1);
            playerMatchStats.setBallsBowled(1);
            playerMatchStatsRepository.save(playerMatchStats);
        }
    }

    public void setBatsmanOut(Long playerId, Long matchId) {
        PlayerMatchStats playerMatchStats = playerMatchStatsRepository.findByPlayerIdAndMatchId(playerId, matchId);
        if (playerMatchStats != null) {
            playerMatchStats.setNotOut(false);
            playerMatchStatsRepository.save(playerMatchStats);
        } else {
            playerMatchStats = new PlayerMatchStats(playerId, matchId);
            playerMatchStats.setNotOut(false);
            playerMatchStats.setBallsFaced(1);
            playerMatchStatsRepository.save(playerMatchStats);
        }
    }

}