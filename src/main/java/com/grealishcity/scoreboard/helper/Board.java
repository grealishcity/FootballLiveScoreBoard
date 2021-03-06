package com.grealishcity.scoreboard.helper;

import java.util.List;
import java.util.stream.IntStream;

import com.grealishcity.scoreboard.model.Game;
import com.grealishcity.scoreboard.model.Team;

public class Board {

    public static void displayMenu() {
        System.out.println("------------------------ MENU ------------------------");
        System.out.println("1. Start a game.");
        System.out.println("2. Finish game.");
        System.out.println("3. Update score.");
        System.out.println("4. Get summary of games by total score.");
        System.out.println("5. Exit.");
    }

    public static void displaySummary(List<Game> games) {
        IntStream.range(0, games.size())
                .forEach(index -> displayGameSummary(index + 1, games.get(index)));
        System.out.println();
    }

    private static void displayGameSummary(int index, Game game) {
        Team homeTeam = game.getHomeTeam();
        Team awayTeam = game.getAwayTeam();
        System.out.printf("%d. %s %d - %s %d%n",
                index, homeTeam.getName(), homeTeam.getCurrentNumberOfGoals(), awayTeam.getName(), awayTeam.getCurrentNumberOfGoals()
        );
    }
}
