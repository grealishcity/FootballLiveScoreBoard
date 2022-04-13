package com.grealishcity.scoreboard.service;

import com.grealishcity.scoreboard.dao.GameDao;
import com.grealishcity.scoreboard.model.Team;
import com.grealishcity.scoreboard.validator.TeamValidator;

public class GameService {

    private final GameDao gameDao;
    private final TeamValidator teamValidator;

    public GameService(GameDao gameDao, TeamValidator teamValidator) {
        this.gameDao = gameDao;
        this.teamValidator = teamValidator;
    }

    public void create(String homeTeamName, String awayTeamName) {
        //TODO: Teams validation, game validation

        Team homeTeam = new Team(homeTeamName);
        Team awayTeam = new Team(awayTeamName);

        gameDao.create(homeTeam, awayTeam);
    }
}
