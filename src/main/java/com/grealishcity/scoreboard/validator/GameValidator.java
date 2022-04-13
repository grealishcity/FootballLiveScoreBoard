package com.grealishcity.scoreboard.validator;

import java.util.function.BiPredicate;

import com.grealishcity.scoreboard.dao.GameDao;
import com.grealishcity.scoreboard.model.Team;

public class GameValidator implements BiPredicate<Team, Team> {

    private final GameDao gameDao;

    public GameValidator(GameDao gameDao) {
        this.gameDao = gameDao;
    }

    @Override
    public boolean test(Team homeTeamName, Team awayTeamName) {
        gameDao.getGames().stream()
                .filter(game -> game.getHomeTeam().equals(homeTeamName))
        return true;
    }
}
