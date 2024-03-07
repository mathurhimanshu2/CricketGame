package com.tekion.cricketgame.service;

import com.tekion.cricketgame.model.TeamMatchStats;
import com.tekion.cricketgame.repository.ITeamMatchStatsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamMatchStatsService {

    private final ITeamMatchStatsRepository teamMatchStatsRepository;

    public TeamMatchStats findByTeamIdAndMatchId(Long teamId, Long matchId) {
        return teamMatchStatsRepository.findByTeamIdAndMatchId(teamId, matchId);
    }

    public int getTeamRuns(Long teamId, Long matchId) {
        TeamMatchStats teamMatchStats = findOrCreateTeamMatchStats(teamId, matchId);
        return teamMatchStats.getTeamRuns();
    }

    public int getTeamWickets(Long teamId, Long matchId) {
        TeamMatchStats teamMatchStats = findOrCreateTeamMatchStats(teamId, matchId);
        return teamMatchStats.getTeamWickets();
    }

    private TeamMatchStats findOrCreateTeamMatchStats(Long teamId, Long matchId) {
        TeamMatchStats teamMatchStats = teamMatchStatsRepository.findByTeamIdAndMatchId(teamId, matchId);
        if (teamMatchStats == null) {
            teamMatchStats = new TeamMatchStats(teamId, matchId);
            teamMatchStatsRepository.save(teamMatchStats);
        }
        return teamMatchStats;
    }

    public void addRuns(Long teamId, Long matchId, int runsToAdd){
        TeamMatchStats teamMatchStats = findByTeamIdAndMatchId(teamId,matchId);
        if (teamMatchStats != null) {
            int currentRuns = teamMatchStats.getTeamRuns();
            int currentBallsFaced = teamMatchStats.getBallsFaced();
            teamMatchStats.setTeamRuns(currentRuns + runsToAdd);
            teamMatchStats.setBallsFaced(currentBallsFaced + 1);

            teamMatchStatsRepository.save(teamMatchStats);
        } else {
            teamMatchStats = new TeamMatchStats(teamId,matchId);
            teamMatchStats.setTeamRuns(runsToAdd);
            teamMatchStats.setBallsFaced(1);
            teamMatchStatsRepository.save(teamMatchStats);
        }
    }

    public void addWicket(Long teamId,Long matchId){
        TeamMatchStats teamMatchStats = findByTeamIdAndMatchId(teamId,matchId);
        if (teamMatchStats != null) {
            int currentRuns = teamMatchStats.getTeamWickets();
            int currentBallsFaced = teamMatchStats.getBallsFaced();
            teamMatchStats.setTeamWickets(currentRuns + 1);
            teamMatchStats.setBallsFaced(currentBallsFaced + 1);

            teamMatchStatsRepository.save(teamMatchStats);
        } else {
            teamMatchStats = new TeamMatchStats(teamId,matchId);
            teamMatchStats.setTeamWickets(1);
            teamMatchStats.setBallsFaced(1);
            teamMatchStatsRepository.save(teamMatchStats);
        }
    }
}