package com.grealishcity.scoreboard.service;

import com.grealishcity.scoreboard.dao.GameDao;
import com.grealishcity.scoreboard.model.Team;

public class GameService {

    private final GameDao gameDao;

    public GameService(GameDao gameDao) {
        this.gameDao = gameDao;
    }

    public void create(String homeTeamName, String awayTeamName) {
        //TODO: Teams validation, game validation

        Team homeTeam = new Team(homeTeamName);
        Team awayTeam = new Team(awayTeamName);

        gameDao.create(homeTeam, awayTeam);
    }
}
