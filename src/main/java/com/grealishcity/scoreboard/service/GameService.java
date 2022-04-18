package com.grealishcity.scoreboard.service;

import java.util.List;

import com.grealishcity.scoreboard.dao.GameDao;
import com.grealishcity.scoreboard.exception.ObjectNotFoundException;
import com.grealishcity.scoreboard.model.Game;
import com.grealishcity.scoreboard.model.Team;
import com.grealishcity.scoreboard.validator.GameValidator;

public class GameService {

    private final GameDao gameDao;
    private final GameValidator gameValidator;

    public GameService(GameDao gameDao, GameValidator gameValidator) {
        this.gameDao = gameDao;
        this.gameValidator = gameValidator;
    }

    public void create(List<Team> teams) {
        Team homeTeam = teams.get(0);
        Team awayTeam = teams.get(1);

        if (gameValidator.checkTeamIsAlreadyOnBoard(homeTeam)) {
            throw new IllegalStateException("Given home team: " + homeTeam.getName() + " is already on board!");
        }

        if (gameValidator.checkTeamIsAlreadyOnBoard(awayTeam)) {
            throw new IllegalStateException("Given away team: " + awayTeam.getName() + " is already on board!");
        }

        gameDao.create(homeTeam, awayTeam);
    }

    public void finish(List<Team> teams) {
        Team homeTeam = teams.get(0);
        Team awayTeam = teams.get(1);

        if (!gameValidator.checkGameExists(homeTeam, awayTeam)) {
            throw new ObjectNotFoundException("Game not found for given home team: " + homeTeam.getName() + " and away team: " + awayTeam.getName());
        }

        gameDao.finish(homeTeam, awayTeam);
    }

    public void update(List<Team> teams) {
        Team homeTeam = teams.get(0);
        Team awayTeam = teams.get(1);

        if (!gameValidator.checkGameExists(homeTeam, awayTeam)) {
            throw new ObjectNotFoundException("Game not found for given home team: " + homeTeam.getName() + " and away team: " + awayTeam.getName());
        }

        gameDao.update(homeTeam, awayTeam);
    }

    public List<Game> getSummaryByTotalScore() {
        return gameDao.getSummaryByTotalScore();
    }
}
