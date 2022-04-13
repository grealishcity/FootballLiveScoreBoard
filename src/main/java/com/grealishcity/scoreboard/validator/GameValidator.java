package com.grealishcity.scoreboard.validator;

import java.util.function.Predicate;

import com.grealishcity.scoreboard.dao.GameDao;
import com.grealishcity.scoreboard.model.Team;

public class GameValidator implements Predicate<Team> {

    private final GameDao gameDao;

    public GameValidator(GameDao gameDao) {
        this.gameDao = gameDao;
    }

    @Override
    public boolean test(Team team) {
        return checkTeamIsAlreadyOnBoard(team);
    }


    private boolean checkTeamIsAlreadyOnBoard(Team teamName) {
        return gameDao.getGames().stream()
                .anyMatch(game -> game.getHomeTeam().equals(teamName) || game.getAwayTeam().equals(teamName));
    }
}
