package com.grealishcity.scoreboard.dao;

import java.util.ArrayList;
import java.util.List;

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

    public List<Game> getGames() {
        return games;
    }
}
