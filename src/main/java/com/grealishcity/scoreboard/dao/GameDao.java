package com.grealishcity.scoreboard.dao;

import java.util.ArrayList;
import java.util.List;

import com.grealishcity.scoreboard.exception.EmptyListException;
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
        if (games.isEmpty()) {
            throw new EmptyListException("Game list is empty. Can't remove game from empty list.");
        }

        games.remove(findGameByHomeTeamAndAwayTeam(homeTeam, awayTeam));
    }

    public void update(Team homeTeam, Team awayTeam) {
        Game gameToUpdate = findGameByHomeTeamAndAwayTeam(homeTeam, awayTeam);
        gameToUpdate.getHomeTeam().setCurrentNumberOfGoals(homeTeam.getCurrentNumberOfGoals());
        gameToUpdate.getAwayTeam().setCurrentNumberOfGoals(awayTeam.getCurrentNumberOfGoals());
    }

    private Game findGameByHomeTeamAndAwayTeam(Team homeTeam, Team awayTeam) {
        return games.stream()
                .filter(game -> game.getHomeTeam().equals(homeTeam) && game.getAwayTeam().equals(awayTeam))
                .findAny()
                .orElseThrow(() -> new ObjectNotFoundException("Game not found for given home team: " + homeTeam + " and away team: " + awayTeam));
    }

    public List<Game> getGames() {
        return games;
    }
}
