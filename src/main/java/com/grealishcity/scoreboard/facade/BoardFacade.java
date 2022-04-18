package com.grealishcity.scoreboard.facade;

import java.util.Scanner;

import com.grealishcity.scoreboard.dao.GameDao;
import com.grealishcity.scoreboard.exception.ObjectNotFoundException;
import com.grealishcity.scoreboard.service.GameService;
import com.grealishcity.scoreboard.service.UserInputService;
import com.grealishcity.scoreboard.helper.Board;
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
                    try {
                        gameService.create(userService.getTeams());
                    } catch (IllegalArgumentException | IllegalStateException e) {
                        System.out.println(e.getMessage() + "\n");
                    }
                    break;
                case 2:
                    try {
                        gameService.finish(userService.getTeams());
                    } catch (IllegalArgumentException | ObjectNotFoundException e) {
                        System.out.println(e.getMessage() + "\n");
                    }
                    break;
                case 3:
                    try {
                        gameService.update(userService.getTeamsWithGoals());
                    } catch (IllegalArgumentException | ObjectNotFoundException e) {
                        System.out.println(e.getMessage() + "\n");
                    }
                    break;
                case 4:
                    Board.displaySummary(gameService.getSummaryByTotalScore());
                    break;
                case 5:
                    System.exit(0);
                default:
                    System.out.println("Incorrect choice. Please try again.\n");
            }
        }
    }
}
