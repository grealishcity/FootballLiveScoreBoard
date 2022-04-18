package com.grealishcity.scoreboard;

import java.util.Scanner;

import com.grealishcity.scoreboard.service.UserService;
import com.grealishcity.scoreboard.utils.Menu;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserService userService = new UserService(scanner);
        int choice;

        while (true) {
            Menu.display();
            choice = userService.getUserChoice();

            switch (choice) {
                case 1:
//                    gameService.create(userService.getTeamName(), userService.getTeamName());
                    break;
                case 2:
//                    finishGame();
                    break;
                case 3:
//                    updateScore();
                    break;
                case 4:
//                    getSummaryByTotalScore();
                    break;
                case 5:
                    System.exit(0);
                default:
                    System.out.print("Incorrect choice. Please try again.\n\n");
            }
        }
    }
}
