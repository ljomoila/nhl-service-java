package com.ljomoila.nhl.facade;


import com.google.gson.internal.LinkedTreeMap;
import com.ljomoila.nhl.domain.*;
import com.ljomoila.nhl.service.NhlServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class NhlFacadeImpl implements NhlFacade {
    private static final Logger logger = LoggerFactory.getLogger(NhlFacadeImpl.class);

    private final NhlServiceImpl service;

    @Autowired
    public NhlFacadeImpl(NhlServiceImpl service) {
        this.service = service;
    }

    @Override
    public List<Team> getTeams() {
        return this.service.getTeams();
    }

    @Override
    public Player getPlayer(String apiLink) {
        return this.service.getPlayer(apiLink);
    }

    @Override
    public LinkedTreeMap getPlayerStats(int id, String type) {
        return this.service.getPlayerStats(id, type);
    }

    @Override
    public List<Game> getGames(String date) {
        List<Team> teams = this.getTeams();
        List<String> gamePaths = this.service.getScheduleGamesByDate(date);
        List<Game> games = new ArrayList<>();

        for (String path : gamePaths) {
           LiveFeed liveFeed = this.service.getLiveFeed(path);

           games.add(this.constructGame(liveFeed, teams));
        }

        return games;
    }

    private Game constructGame(LiveFeed liveFeed, List<Team> teams) {
        GameData gameData = liveFeed.getGameData();
        LiveData liveData = liveFeed.getLiveData();

        Game.GameStatus status = Game.GameStatus.valueOf(gameData.getStatus().getDetailedState());

        LineScore lineScore = liveData.getLineScore();
        String period = lineScore.getCurrentPeriodOrdinal();
        String timeRemaining = lineScore.getCurrentPeriodTimeRemaining();

        if (period != null && timeRemaining != null) {
            timeRemaining = period + "\n" + timeRemaining.toUpperCase() + ")";
        }

        BoxScore boxScore = liveData.getBoxScore();
        LinkedTreeMap teamBoxScores = boxScore.getTeams();
        LinkedTreeMap teamLineScores = lineScore.getTeams();

        GameTeam homeTeam = this.constructGameTeam((LinkedTreeMap) teamLineScores.get("home"),
                (LinkedTreeMap) teamBoxScores.get("home"));
        GameTeam awayTeam = this.constructGameTeam((LinkedTreeMap) teamLineScores.get("away"),
                (LinkedTreeMap) teamBoxScores.get("away"));

        // find and set short names for teams
        for (Team team :teams) {
            if (team.getId() == homeTeam.getId()) homeTeam.setShortName(team.getShortName());
            else if (team.getId() == awayTeam.getId()) awayTeam.setShortName(team.getShortName());
        }

        return new Game(homeTeam, awayTeam, timeRemaining, status, period);
    }

    private GameTeam constructGameTeam(LinkedTreeMap lineScoreMap, LinkedTreeMap boxScoreMap) {
        LinkedTreeMap boxScore = (LinkedTreeMap) boxScoreMap.get("team");
        Double teamId = (Double) boxScore.get("id");
        String teamName = (String) boxScore.get("name");
        String apiLink = (String) boxScore.get("link");
        int goals = ((Double) lineScoreMap.get("goals")).intValue();

        LinkedTreeMap players = (LinkedTreeMap) boxScoreMap.get("players");
        List<GamePlayer> playersWithStats = new ArrayList<>();

        Collection c = players.values();
        Iterator itr = c.iterator();

        while(itr.hasNext()) {
            LinkedTreeMap player = (LinkedTreeMap) itr.next();
            LinkedTreeMap stats = (LinkedTreeMap) player.get("stats");

            if (stats.size() == 0) continue;

            LinkedTreeMap skaterStats = (LinkedTreeMap) stats.get("skaterStats");
            LinkedTreeMap goalieStats = (LinkedTreeMap) stats.get("goalieStats");

            if (skaterStats == null && goalieStats == null) continue;

            LinkedTreeMap person = (LinkedTreeMap) player.get("person");
            String playerApiLink = (String) person.get("link");
            GamePlayer playerWithStats = null;

            if (skaterStats != null) playerWithStats = this.constructSkater(playerApiLink, skaterStats);
            else if (goalieStats != null) playerWithStats = this.constructGoalie(playerApiLink, goalieStats);

            if (playerWithStats != null) playersWithStats.add((playerWithStats));
        }

        playersWithStats = sortPlayersWithStats(playersWithStats);

        return new GameTeam(teamId.intValue(), teamName, goals, playersWithStats, apiLink);
    }

    // Sorts game players by position and points, with the emphases on goals
    private List<GamePlayer> sortPlayersWithStats(List<GamePlayer> players) {
        Comparator<GamePlayer> pointsComparison = (GamePlayer a, GamePlayer b) -> {
            int pointsA = a.getGoals() + a.getAssists();
            int pointsB = b.getGoals() + b.getAssists();
            int comparison = pointsA > pointsB ? -1 : 1;

            if (pointsA == pointsB) comparison = a.getGoals() > b.getGoals() ? -1 : 1;

            return comparison;
        };

        Collections.sort(players, pointsComparison);
        players.sort(Comparator.comparing(GamePlayer::getPosition));

        return players;
    }

    private GamePlayer constructSkater(String apiLink, LinkedTreeMap skaterStats) {
        int goals = (int) Math.round((Double) skaterStats.get("goals"));
        int assists = (int) Math.round((Double) skaterStats.get("assists"));

        if (goals == 0 && assists == 0) return null;

        Player player = this.getPlayer(apiLink);

        return new GamePlayer(player.getId(), player.getFullName(), player.getLastName(), player.getNationality(), assists, goals,
                apiLink);
    }

    private GameGoalie constructGoalie(String apiLink, LinkedTreeMap goalieStats) {
        Object savePercentage = goalieStats.get("savePercentage");

        if (savePercentage == null) return null;

        Player player = this.getPlayer(apiLink);

        int saves = (int) Math.round((Double) goalieStats.get("saves"));
        int shots = (int) Math.round((Double) goalieStats.get("shots"));
        int goals = goalieStats.get("_goals") != null ? (int)  Math.round((Double) goalieStats.get("_goals")) : 0;
        int assists = goalieStats.get("_assists") != null ? (int) Math.round((Double) goalieStats.get("_assists")) : 0;

        return new GameGoalie(player.getId(), player.getFullName(), player.getLastName(), player.getNationality(), assists, goals, shots,
                saves, (Double) savePercentage, apiLink);
    }
}
