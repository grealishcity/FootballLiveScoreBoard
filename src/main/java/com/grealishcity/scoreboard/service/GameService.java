package com.grealishcity.scoreboard.service;

import com.grealishcity.scoreboard.dao.GameDao;
import com.grealishcity.scoreboard.model.Team;
import com.grealishcity.scoreboard.validator.GameValidator;
import com.grealishcity.scoreboard.validator.TeamValidator;

public class GameService {

    private final GameDao gameDao;
    private final TeamValidator teamValidator;
    private final GameValidator gameValidator;

    public GameService(GameDao gameDao, TeamValidator teamValidator, GameValidator gameValidator) {
        this.gameDao = gameDao;
        this.teamValidator = teamValidator;
        this.gameValidator = gameValidator;
    }

    public void create(String homeTeamName, String awayTeamName) {
        //TODO: Teams validation, game validation
        validateTeamsNames(homeTeamName, awayTeamName);

        Team homeTeam = new Team(homeTeamName);
        Team awayTeam = new Team(awayTeamName);

        validateTeamIsAlreadyOnBoard(homeTeam, awayTeam);

        gameDao.create(homeTeam, awayTeam);
    }

    public void finish(String homeTeamName, String awayTeamName) {
        validateTeamsNames(homeTeamName, awayTeamName);
        gameDao.finish(new Team(homeTeamName), new Team(awayTeamName));
    }

    private void validateTeamsNames(String homeTeamName, String awayTeamName) {
        if (!teamValidator.test(homeTeamName)) {
            throw new IllegalArgumentException("Illegal home team name given: " + homeTeamName);
        } else if (!teamValidator.test(awayTeamName)) {
            throw new IllegalArgumentException("Illegal away team name given: " + awayTeamName);
        }
    }
    private void validateTeamIsAlreadyOnBoard(Team homeTeam, Team awayTeam) {
        if (gameValidator.test(homeTeam)) {
            throw new IllegalStateException("Team is already on board: " + homeTeam.getName());
        } else if (gameValidator.test(awayTeam)) {
            throw new IllegalStateException("Team is already on board: " + awayTeam.getName());
        }
    }
}
