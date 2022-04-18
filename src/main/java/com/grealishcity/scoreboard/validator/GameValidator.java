package com.grealishcity.scoreboard.validator;

import com.grealishcity.scoreboard.dao.GameDao;
import com.grealishcity.scoreboard.model.Team;

public class GameValidator {

    private final GameDao gameDao;

    public GameValidator(GameDao gameDao) {
        this.gameDao = gameDao;
    }

    public boolean checkTeamIsAlreadyOnBoard(Team team) {
        return gameDao.getGames().stream()
                .anyMatch(game -> game.getHomeTeam().equals(team) || game.getAwayTeam().equals(team));
    }

    public boolean checkGameExists(Team homeTeam, Team awayTeam) {
        return gameDao.getGames().stream()
                .anyMatch(game -> game.getHomeTeam().getName().equals(homeTeam.getName()) && game.getAwayTeam().getName().equals(awayTeam.getName()));
    }

}
