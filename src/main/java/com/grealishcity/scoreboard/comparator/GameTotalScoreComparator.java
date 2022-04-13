package com.grealishcity.scoreboard.comparator;

import java.util.Comparator;

import com.grealishcity.scoreboard.model.Game;

public class GameTotalScoreComparator implements Comparator<Game> {

    @Override
    public int compare(Game firstGame, Game secondGame) {
        int totalNumberOfGoalsFirstGame = getTotalNumberOfGoals(firstGame);
        int totalNumberOfGoalsSecondGame = getTotalNumberOfGoals(secondGame);

        if (totalNumberOfGoalsFirstGame != totalNumberOfGoalsSecondGame) {
            return Integer.compare(totalNumberOfGoalsFirstGame, totalNumberOfGoalsSecondGame);
        } else {
            return firstGame.getCreated().compareTo(secondGame.getCreated());
        }
    }

    private int getTotalNumberOfGoals(Game game) {
        return game.getHomeTeam().getCurrentNumberOfGoals() + game.getAwayTeam().getCurrentNumberOfGoals();
    }
}
