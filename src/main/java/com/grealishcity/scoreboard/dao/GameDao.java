package com.grealishcity.scoreboard.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.grealishcity.scoreboard.comparator.GameTotalScoreComparator;
import com.grealishcity.scoreboard.exception.ObjectNotFoundException;
import com.grealishcity.scoreboard.model.Game;
import com.grealishcity.scoreboard.model.Team;

public class GameDao {

    private final List<Game> games;

    public GameDao() {
        this.games = new ArrayList<>();
    }

    public void create(Team homeTeam, Team awayTeam) {
        games.add(new Game(homeTeam, awayTeam));
    }

    public void finish(Team homeTeam, Team awayTeam) {
        games.remove(findGameByHomeTeamAndAwayTeam(homeTeam, awayTeam));
    }

    public void update(Team homeTeam, Team awayTeam) {
        Game gameToUpdate = findGameByHomeTeamAndAwayTeam(homeTeam, awayTeam);
        gameToUpdate.getHomeTeam().setCurrentNumberOfGoals(homeTeam.getCurrentNumberOfGoals());
        gameToUpdate.getAwayTeam().setCurrentNumberOfGoals(awayTeam.getCurrentNumberOfGoals());
    }

    private Game findGameByHomeTeamAndAwayTeam(Team homeTeam, Team awayTeam) {
        return games.stream()
                .filter(game -> isHomeTeamAndAwayTeamPresent(game, homeTeam, awayTeam))
                .findAny()
                .orElseThrow(() -> new ObjectNotFoundException("Game not found for given home team: " + homeTeam.getName() + " and away team: " + awayTeam.getName()));
    }

    private boolean isHomeTeamAndAwayTeamPresent(Game game, Team homeTeam, Team awayTeam) {
        return game.getHomeTeam().getName().equals(homeTeam.getName()) && game.getAwayTeam().getName().equals(awayTeam.getName());
    }

    public List<Game> getSummaryByTotalScore() {
        return games.stream()
                .sorted(new GameTotalScoreComparator())
                .collect(Collectors.toList());
    }

    public List<Game> getGames() {
        return games;
    }
}
