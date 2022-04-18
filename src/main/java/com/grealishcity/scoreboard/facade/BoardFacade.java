package com.grealishcity.scoreboard.facade;

import java.util.Scanner;

import com.grealishcity.scoreboard.dao.GameDao;
import com.grealishcity.scoreboard.service.GameService;
import com.grealishcity.scoreboard.service.UserInputService;
import com.grealishcity.scoreboard.utils.Board;
import com.grealishcity.scoreboard.validator.GameValidator;
import com.grealishcity.scoreboard.validator.TeamValidator;

public class BoardFacade {

    private final GameDao gameDao = new GameDao();
    private final UserInputService userService = new UserInputService(new Scanner(System.in), new TeamValidator());
    private final GameService gameService = new GameService(gameDao, new GameValidator(gameDao));

    public void start() {
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
