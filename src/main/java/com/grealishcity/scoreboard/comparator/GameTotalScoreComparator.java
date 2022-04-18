package com.grealishcity.scoreboard.comparator;

import java.util.Comparator;

import com.grealishcity.scoreboard.model.Game;

public class GameTotalScoreComparator implements Comparator<Game> {

    @Override
    public int compare(Game firstGame, Game secondGame) {
        int totalNumberOfGoalsFirstGame = calculateTotalNumberOfGoals(firstGame);
        int totalNumberOfGoalsSecondGame = calculateTotalNumberOfGoals(secondGame);

        if (totalNumberOfGoalsFirstGame != totalNumberOfGoalsSecondGame) {
            return Integer.compare(totalNumberOfGoalsSecondGame, totalNumberOfGoalsFirstGame);
        } else {
            return firstGame.getCreated().compareTo(secondGame.getCreated());
        }
    }

    private int calculateTotalNumberOfGoals(Game game) {
        return game.getHomeTeam().getCurrentNumberOfGoals() + game.getAwayTeam().getCurrentNumberOfGoals();
    }
}
