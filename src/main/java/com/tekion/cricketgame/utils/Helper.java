package com.tekion.cricketgame.utils;

import com.tekion.cricketgame.dto.*;
import com.tekion.cricketgame.model.*;
import com.tekion.cricketgame.service.MatchStateService;
import com.tekion.cricketgame.service.PlayerMatchStatsService;
import com.tekion.cricketgame.service.ScoreCardService;
import com.tekion.cricketgame.service.TeamMatchStatsService;
import com.tekion.cricketgame.utils.enums.Ball;
import com.tekion.cricketgame.utils.enums.MatchStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Random;
@Component
public class Helper {

    @Autowired
    PlayerMatchStatsService playerMatchStatsService;

    @Autowired
    TeamMatchStatsService teamMatchStatsService;

    @Autowired
    ScoreCardService scoreCardService;

    @Autowired
    MatchStateService matchStateService;

    private Ball simulateBall() {
        Random random = new Random();
        int randomNumber = random.nextInt(7);

        switch (randomNumber) {
            case 1:
                return Ball.ONE_RUN;
            case 2:
                return Ball.TWO_RUNS;
            case 3:
                return Ball.THREE_RUNS;
            case 4:
                return Ball.FOUR_RUNS;
            case 5:
                return Ball.SIX_RUNS;
            case 6:
                return Ball.OUT;
            default:
                return Ball.DOT_BALL;
        }
    }

    public MatchSummary simulateMatch(Match match, int ballsToBeSimulated,MatchState matchState) {
        InningSummary firstInnings = simulateFirstInnings(match, ballsToBeSimulated,matchState);

        if(matchState.getMatchStatus()==MatchStatus.SECOND_INNINGS){
            InningSummary secondInnings;
            if (ballsToBeSimulated> matchState.getBallsPlayedInFirstInnings()){
                secondInnings = simulateSecondInnings(match,ballsToBeSimulated - matchState.getBallsPlayedInFirstInnings(), matchState);
            } else {
                secondInnings = simulateSecondInnings(match,ballsToBeSimulated,matchState);
            }
            scoreCardService.fillScoreCard(firstInnings,secondInnings,match);
            return getMatchSummary(matchState);
        }
        scoreCardService.fillScoreCard(firstInnings,new InningSummary(),match);
        return getMatchSummary(matchState);
    }
    private String determineMatchResult(MatchState matchState) {

        if (matchState.getMatchStatus() == MatchStatus.MATCH_OVER){
            int firstInningsRuns = matchState.getRunsMadeInFirstInnings();
            int secondInningsRuns = matchState.getRunsMadeInSecondInnings();

            if (firstInningsRuns > secondInningsRuns) {
                return "Team 1 won by " + (firstInningsRuns - secondInningsRuns) + " runs";
            } else if (firstInningsRuns < secondInningsRuns) {
                return "Team 2 won by " + (10 - matchState.getWicketsTakenInSecondInnings()) + " wickets";
            } else {
                return "Match tied";
            }
        }

        return "Match Still In Play";

    }


    private MatchSummary getMatchSummary(MatchState matchState){

        MatchSummary matchSummary = new MatchSummary();

        matchSummary.setTeam1Runs(matchState.getRunsMadeInFirstInnings());
        matchSummary.setTeam1Wickets(matchState.getWicketsTakenInFirstInnings());

        matchSummary.setTeam2Runs(matchState.getRunsMadeInSecondInnings());
        matchSummary.setTeam2Wicket(matchState.getWicketsTakenInSecondInnings());

        String matchResult = determineMatchResult(matchState);

        matchSummary.setResult(matchResult);

        return matchSummary;
    }


    private InningSummary simulateFirstInnings(Match match, int ballsTobeSimulated, MatchState matchState) {

        int totalBalls = match.getOvers() * 6;
        int currentBatsman = matchState.getCurrentFirstInningBatsman();
        int currentBowler = matchState.getCurrentFirstInningBowler();

        int ballsPlayedInFirstInnings = matchState.getBallsPlayedInFirstInnings();

        for(int ballNumber = 1; ballNumber <= ballsTobeSimulated && currentBatsman < 10; ballNumber++){

            if(ballNumber%6==0){
                currentBowler = changeBowler(currentBowler);
            }
            Ball result = simulateBall();

            switch (result){

                case DOT_BALL:
                case ONE_RUN:
                case TWO_RUNS:
                case THREE_RUNS:
                case FOUR_RUNS:
                case SIX_RUNS:
                    playerMatchStatsService.upsertBatsmanRuns(match.getTeam1().getPlayers().get(currentBatsman).getPlayerId(), match.getMatchId(),result.getValue());
                    playerMatchStatsService.upsertBowlerRuns(match.getTeam2().getPlayers().get(currentBowler).getPlayerId(), match.getMatchId(),result.getValue());
                    teamMatchStatsService.addRuns(match.getTeam1().getTeamId(), match.getMatchId(), result.getValue());
                    break;
                case OUT:
                    teamMatchStatsService.addWicket(match.getTeam1().getTeamId(), match.getMatchId());
                    playerMatchStatsService.addWicket(match.getTeam2().getPlayers().get(currentBowler).getPlayerId(), match.getMatchId());
                    playerMatchStatsService.setBatsmanOut(match.getTeam1().getPlayers().get(currentBatsman).getPlayerId(), match.getMatchId());
                    currentBatsman++;
                    break;
            }
            ballsPlayedInFirstInnings++;

            if (ballsPlayedInFirstInnings >= totalBalls || currentBatsman >= 10) {
                matchState.setMatchStatus(MatchStatus.SECOND_INNINGS);
            }
            matchState.setCurrentFirstInningBatsman(currentBatsman);
            matchState.setCurrentFirstInningBowler(currentBowler);
            matchState.setBallsPlayedInFirstInnings(ballsPlayedInFirstInnings);
            matchState.setRunsMadeInFirstInnings(teamMatchStatsService.getTeamRuns(match.getTeam1().getTeamId(), match.getMatchId()));
            matchState.setWicketsTakenInFirstInnings(teamMatchStatsService.getTeamWickets(match.getTeam1().getTeamId(), match.getMatchId()));
            matchStateService.save(matchState);

        }
        matchState.setCurrentFirstInningBatsman(currentBatsman);
        matchState.setCurrentFirstInningBowler(currentBowler);
        matchState.setBallsPlayedInFirstInnings(ballsPlayedInFirstInnings);
        matchState.setRunsMadeInFirstInnings(teamMatchStatsService.getTeamRuns(match.getTeam1().getTeamId(), match.getMatchId()));
        matchState.setWicketsTakenInFirstInnings(teamMatchStatsService.getTeamWickets(match.getTeam1().getTeamId(), match.getMatchId()));
        matchStateService.save(matchState);


        return fillInnings(match.getTeam1(),match.getTeam2(), match.getMatchId());

    }


    private InningSummary fillInnings(Team team1,Team team2, Long matchId){

        BattingDetails battingDetails = new BattingDetails();
        BowlingDetails bowlingDetails = new BowlingDetails();
        ArrayList<PlayerBattingStats> playerBattingStatsArrayList = new ArrayList<>();
        ArrayList<PlayerBowlingStats> playerBowlingStatsArrayList = new ArrayList<>();


        for (Player player : team1.getPlayers()) {
            PlayerMatchStats playerMatchStats = playerMatchStatsService.findByPlayerIdAndMatchId(player.getPlayerId(), matchId);

            PlayerBattingStats battingStats = new PlayerBattingStats();
            battingStats.setPlayerId(player.getPlayerId());
            battingStats.setRuns(playerMatchStats.getRuns());
            battingStats.setBalls(playerMatchStats.getBallsFaced());

            playerBattingStatsArrayList.add(battingStats);
        }

        team2.getPlayers().forEach(player -> {
            PlayerMatchStats playerMatchStats = playerMatchStatsService.findByPlayerIdAndMatchId(player.getPlayerId(), matchId);

            PlayerBowlingStats bowlingStats = new PlayerBowlingStats();
            bowlingStats.setPlayerId(playerMatchStats.getPlayerId());
            bowlingStats.setWickets(playerMatchStats.getWicketsTaken());
            bowlingStats.setOvers(playerMatchStats.getBallsBowled()/6);

            playerBowlingStatsArrayList.add(bowlingStats);

        });
        battingDetails.setTeamId(team1.getTeamId());
        battingDetails.setPlayerBattingList(playerBattingStatsArrayList);

        bowlingDetails.setTeamId(team2.getTeamId());
        bowlingDetails.setPlayerBallingList(playerBowlingStatsArrayList);

        int teamRuns = teamMatchStatsService.getTeamRuns(team1.getTeamId(), matchId);
        int teamWickets = teamMatchStatsService.getTeamWickets(team1.getTeamId(), matchId);

        return new InningSummary(battingDetails,bowlingDetails,teamRuns, teamWickets);
    }


    private InningSummary simulateSecondInnings(Match match, int ballToBeSimulated, MatchState matchState) {

        int totalBalls = match.getOvers() * 6;
        int currentBatsman = matchState.getCurrentSecondInningBatsman();
        int currentBowler = matchState.getCurrentSecondInningBowler();
        int ballsPlayedInSecondInnings = matchState.getBallsPlayedInSecondInnings();

        for(int ballNumber = 1; ballNumber <= ballToBeSimulated && currentBatsman < 10; ballNumber++){

            if (teamMatchStatsService.getTeamRuns(match.getTeam1().getTeamId(), match.getMatchId())< teamMatchStatsService.getTeamRuns(match.getTeam2().getTeamId(), match.getMatchId())){

                matchState.setCurrentSecondInningBatsman(currentBatsman);
                matchState.setCurrentSecondInningBowler(currentBowler);
                matchState.setMatchStatus(MatchStatus.MATCH_OVER);
                matchState.setBallsPlayedInSecondInnings(ballsPlayedInSecondInnings);
                matchState.setRunsMadeInSecondInnings(teamMatchStatsService.getTeamRuns(match.getTeam2().getTeamId(), match.getMatchId()));
                matchState.setWicketsTakenInSecondInnings(teamMatchStatsService.getTeamWickets(match.getTeam2().getTeamId(), match.getMatchId()));
                matchStateService.save(matchState);
                return fillInnings(match.getTeam2(),match.getTeam1(), match.getMatchId());
            }

            if(ballNumber%6 == 0){
                currentBowler = changeBowler(currentBowler);
            }
            Ball result = simulateBall();

            switch (result){

                case DOT_BALL:
                case ONE_RUN:
                case TWO_RUNS:
                case THREE_RUNS:
                case FOUR_RUNS:
                case SIX_RUNS:
                    playerMatchStatsService.upsertBatsmanRuns(match.getTeam2().getPlayers().get(currentBatsman).getPlayerId(), match.getMatchId(),result.getValue());
                    playerMatchStatsService.upsertBowlerRuns(match.getTeam1().getPlayers().get(currentBowler).getPlayerId(), match.getMatchId(),result.getValue());
                    teamMatchStatsService.addRuns(match.getTeam2().getTeamId(), match.getMatchId(), result.getValue());
                    break;
                case OUT:
                    teamMatchStatsService.addWicket(match.getTeam2().getTeamId(), match.getMatchId());
                    playerMatchStatsService.setBatsmanOut(match.getTeam2().getPlayers().get(currentBatsman).getPlayerId(), match.getMatchId());
                    playerMatchStatsService.addWicket(match.getTeam1().getPlayers().get(currentBowler).getPlayerId(), match.getMatchId());
                    currentBatsman++;
                    break;
            }

            ballsPlayedInSecondInnings++;
            matchState.setCurrentSecondInningBatsman(currentBatsman);
            matchState.setCurrentSecondInningBowler(currentBowler);
            matchState.setBallsPlayedInSecondInnings(ballsPlayedInSecondInnings);
            matchState.setRunsMadeInSecondInnings(teamMatchStatsService.getTeamRuns(match.getTeam2().getTeamId(), match.getMatchId()));
            matchState.setWicketsTakenInSecondInnings(teamMatchStatsService.getTeamWickets(match.getTeam2().getTeamId(), match.getMatchId()));
            matchStateService.save(matchState);

            if (ballsPlayedInSecondInnings >= totalBalls || currentBatsman>=10) {
                matchState.setMatchStatus(MatchStatus.MATCH_OVER);
                break;
            }

        }
        matchState.setCurrentSecondInningBatsman(currentBatsman);
        matchState.setCurrentSecondInningBowler(currentBowler);
        matchState.setBallsPlayedInSecondInnings(ballsPlayedInSecondInnings);
        matchState.setRunsMadeInSecondInnings(teamMatchStatsService.getTeamRuns(match.getTeam2().getTeamId(), match.getMatchId()));
        matchState.setWicketsTakenInSecondInnings(teamMatchStatsService.getTeamWickets(match.getTeam2().getTeamId(), match.getMatchId()));
        matchStateService.save(matchState);
        return fillInnings(match.getTeam2(),match.getTeam1(), match.getMatchId());

    }

    private int changeBowler(int currentBowler) {
        Random random = new Random();
        int randomNumber;
        do {
            randomNumber = random.nextInt(11);
        } while (randomNumber == currentBowler);
        return randomNumber;
    }
}
//}0LMNXi25wWL2KA3F
//mysql --user avnadmin --password=AVNS_3Zuhwp9lCt47wJAHj60 --host mysql-himanshu-cricket-game.a.aivencloud.com --port 22237 cricket-game
//        AVNS_3Zuhwp9lCt47wJAHj60