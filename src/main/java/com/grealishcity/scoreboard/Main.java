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
        TeamValidator teamValidator = new TeamValidator();
        UserService userService = new UserService(scanner, teamValidator);
        GameDao gameDao = new GameDao();
        GameValidator gameValidator = new GameValidator(gameDao);
        GameService gameService = new GameService(gameDao, gameValidator);
        int choice;

        while (true) {
            Board.displayMenu();
            choice = userService.getUserChoice();

            switch (choice) {
                case 1:
                    gameService.create(userService.getTeams());
                    break;
                case 2:
                    gameService.finish(userService.getTeams());
                    break;
                case 3:
                    gameService.update(userService.getTeamsWithGoals());
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
