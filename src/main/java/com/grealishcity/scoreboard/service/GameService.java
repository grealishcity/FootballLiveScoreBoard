package com.grealishcity.scoreboard.service;

import java.util.List;

import com.grealishcity.scoreboard.dao.GameDao;
import com.grealishcity.scoreboard.exception.ObjectNotFoundException;
import com.grealishcity.scoreboard.model.Game;
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

    public void create(List<String> teamsNames) {
        validateTeamsNames(teamsNames.get(0), teamsNames.get(1));

        Team homeTeam = new Team(teamsNames.get(0));
        Team awayTeam = new Team(teamsNames.get(1));

        validateTeamIsAlreadyOnBoard(homeTeam, awayTeam);

        gameDao.create(homeTeam, awayTeam);
    }

    public void finish(List<String> teamsNames) {
        validateTeamsNames(teamsNames.get(0), teamsNames.get(1));

        Team homeTeam = new Team(teamsNames.get(0));
        Team awayTeam = new Team(teamsNames.get(1));

        if (!gameValidator.checkGameExists(homeTeam, awayTeam)) {
            throw new ObjectNotFoundException("Game not found for given home team: " + teamsNames.get(0) + " and away team: " + teamsNames.get(1));
        }
        gameDao.finish(homeTeam, awayTeam);
    }

    public void update(List<String> teamsNames) {
        validateTeamsNames(teamsNames.get(0), teamsNames.get(1));

        Team homeTeam = new Team(teamsNames.get(0));
        Team awayTeam = new Team(teamsNames.get(1));

        gameValidator.checkGameExists(homeTeam, awayTeam);
        gameDao.update(homeTeam, awayTeam);
    }

    public List<Game> getSummaryByTotalScore() {
        return gameDao.getSummaryByTotalScore();
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
