package com.grealishcity.scoreboard;

import java.util.Scanner;

import com.grealishcity.scoreboard.service.UserService;
import com.grealishcity.scoreboard.utils.Menu;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            Menu.display();
            choice = new UserService(scanner).getUserChoice();

            switch (choice) {
                case 1:
//                    startGame();
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
