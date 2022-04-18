package com.grealishcity.scoreboard;

import java.util.Scanner;

import com.grealishcity.scoreboard.dao.GameDao;
import com.grealishcity.scoreboard.service.GameService;
import com.grealishcity.scoreboard.service.UserService;
import com.grealishcity.scoreboard.utils.Board;
import com.grealishcity.scoreboard.validator.GameValidator;
import com.grealishcity.scoreboard.validator.TeamValidator;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserService userService = new UserService(scanner);
        GameDao gameDao = new GameDao();
        TeamValidator teamValidator = new TeamValidator();
        GameValidator gameValidator = new GameValidator(gameDao);
        GameService gameService = new GameService(gameDao, teamValidator, gameValidator);
        int choice;

        while (true) {
            Board.displayMenu();
            choice = userService.getUserChoice();

            switch (choice) {
                case 1:
                    gameService.create(userService.getTeamsNames());
                    break;
                case 2:
                    gameService.finish(userService.getTeamsNames());
                    break;
                case 3:
                    gameService.update(userService.getTeamsNamesWithGoals());
                    break;
                case 4:
                    Board.displaySummary(gameService.getSummaryByTotalScore());
                    break;
                case 5:
                    System.exit(0);
                default:
                    System.out.print("Incorrect choice. Please try again.\n\n");
            }
        }
    }
}
