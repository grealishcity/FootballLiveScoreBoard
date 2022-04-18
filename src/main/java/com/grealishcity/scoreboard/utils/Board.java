package com.grealishcity.scoreboard.utils;

import java.util.List;
import java.util.stream.IntStream;

import com.grealishcity.scoreboard.model.Game;

public class Board {

    public static void displayMenu() {
        System.out.println("------------------------ MENU ------------------------");
        System.out.println("1. Start a game.");
        System.out.println("2. Finish game.");
        System.out.println("3. Update score.");
        System.out.println("4. Get summary of games by total score.");
        System.out.println("5. Exit.");
    }

    public static void displaySummary(List<Game> summaryByTotalScore) {
        IntStream.of(0, summaryByTotalScore.size())
                .forEach(index -> System.out.println(index));
    }
}
//
//IntStream.range(0, list.size())
//        .forEach(index -> System.out.println(String.format("[%d] : %s", index, list.get(index))));
