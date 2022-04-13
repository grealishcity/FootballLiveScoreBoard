package com.grealishcity.scoreboard.service;

import com.grealishcity.scoreboard.dao.GameDao;
import com.grealishcity.scoreboard.exception.ObjectNotFoundException;
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
        validateTeamsNames(homeTeamName, awayTeamName);

        Team homeTeam = new Team(homeTeamName);
        Team awayTeam = new Team(awayTeamName);

        validateTeamIsAlreadyOnBoard(homeTeam, awayTeam);

        gameDao.create(homeTeam, awayTeam);
    }

    public void finish(String homeTeamName, String awayTeamName) {
        validateTeamsNames(homeTeamName, awayTeamName);

        Team homeTeam = new Team(homeTeamName);
        Team awayTeam = new Team(awayTeamName);

        if (!gameValidator.checkGameExists(homeTeam, awayTeam)) {
            throw new ObjectNotFoundException("Game not found for given home team: " + homeTeamName + " and away team: " + awayTeamName);
        }
        gameDao.finish(homeTeam, awayTeam);
    }

    public void update(Team homeTeam, Team awayTeam) {
        gameValidator.checkGameExists(homeTeam, awayTeam);
        gameDao.update(homeTeam, awayTeam);
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
