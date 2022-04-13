package com.grealishcity.scoreboard.dao;

import java.util.ArrayList;
import java.util.List;

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
        Game gameToFinish = games.stream()
                .filter(game -> game.getHomeTeam().equals(homeTeam) && game.getAwayTeam().equals(awayTeam))
                .findAny()
                .orElseThrow(() -> new ObjectNotFoundException("Game not found"));

        games.remove(gameToFinish);
    }

    public List<Game> getGames() {
        return games;
    }
}
