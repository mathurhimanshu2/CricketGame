package com.tekion.cricketgame.utils;

import com.tekion.cricketgame.dto.*;
import com.tekion.cricketgame.model.Match;
import com.tekion.cricketgame.model.Player;
import com.tekion.cricketgame.model.PlayerMatchStats;
import com.tekion.cricketgame.model.Team;
import com.tekion.cricketgame.service.PlayerMatchStatsService;
import com.tekion.cricketgame.service.ScoreCardService;
import com.tekion.cricketgame.service.TeamMatchStatsService;
import com.tekion.cricketgame.utils.enums.Ball;
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

    public void simulateMatch(Match match){

        InningSummary firstInnings = simulateFirstInnings(match);
        InningSummary secondInnings = simulateSecondInnings(match);

        scoreCardService.fillScoreCard(firstInnings,secondInnings,match);

    }

    private InningSummary simulateFirstInnings(Match match) {


        int totalBalls = match.getOvers()*6;
        int currentBatsman = 0;
        int currentBaller = 1;

        for(int ballNumber = 1; ballNumber <= totalBalls && currentBatsman < 11; ballNumber++){

            if(ballNumber%6==0){
                currentBaller = changeBowler(currentBaller);
            }
            Ball result = simulateBall();

            switch (result){

                case DOT_BALL:
                    break;
                case ONE_RUN:
                case TWO_RUNS:
                case THREE_RUNS:
                case FOUR_RUNS:
                case SIX_RUNS:
                    playerMatchStatsService.addBatsmanRuns(match.getTeam1().getPlayers().get(currentBatsman).getPlayerId(), match.getMatchId(),result.getValue());
                    playerMatchStatsService.addBowlerRuns(match.getTeam2().getPlayers().get(currentBaller).getPlayerId(), match.getMatchId(),result.getValue());
                    teamMatchStatsService.addRuns(match.getTeam1().getTeamId(), match.getMatchId(), result.getValue());
                    break;
                case OUT:
                    teamMatchStatsService.addWicket(match.getTeam1().getTeamId(), match.getMatchId());
                    playerMatchStatsService.addWicket(match.getTeam2().getPlayers().get(currentBaller).getPlayerId(), match.getMatchId());
                    currentBatsman++;
                    break;
            }
        }

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

        return new InningSummary(battingDetails,bowlingDetails);
    }

    private InningSummary simulateSecondInnings(Match match) {

        int totalBalls = match.getOvers()*6;
        int currentBatsman = 0;
        int currentBaller = 1;

        for(int ballNumber = 1; ballNumber <= totalBalls && currentBatsman < 11; ballNumber++){

            if(ballNumber%6 == 0){
                currentBaller = changeBowler(currentBaller);
            }
            Ball result = simulateBall();

            switch (result){

                case DOT_BALL:
                    break;
                case ONE_RUN:
                case TWO_RUNS:
                case THREE_RUNS:
                case FOUR_RUNS:
                case SIX_RUNS:
                    playerMatchStatsService.addBatsmanRuns(match.getTeam2().getPlayers().get(currentBatsman).getPlayerId(), match.getMatchId(),result.getValue());
                    playerMatchStatsService.addBowlerRuns(match.getTeam1().getPlayers().get(currentBaller).getPlayerId(), match.getMatchId(),result.getValue());
                    teamMatchStatsService.addRuns(match.getTeam2().getTeamId(), match.getMatchId(), result.getValue());
                    break;
                case OUT:
                    teamMatchStatsService.addWicket(match.getTeam2().getTeamId(), match.getMatchId());
                    playerMatchStatsService.addWicket(match.getTeam1().getPlayers().get(currentBaller).getPlayerId(), match.getMatchId());
                    currentBatsman++;
                    break;
            }

            if (teamMatchStatsService.getTeamRuns(match.getTeam1().getTeamId(), match.getMatchId())< teamMatchStatsService.getTeamRuns(match.getTeam2().getTeamId(), match.getMatchId())){

                return fillInnings(match.getTeam2(),match.getTeam1(), match.getMatchId());
            }
        }
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
//mysql --user avnadmin --password=AVNS_3Zuhwp9lCt47wJAHj60 --host mysql-himanshu-cricket-game.a.aivencloud.com --port 22237 defaultdb
//        AVNS_3Zuhwp9lCt47wJAHj60
