package com.grealishcity.scoreboard;

import java.util.Scanner;

import com.grealishcity.scoreboard.utils.Menu;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            Menu.display();
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
//                    startGame();
                    System.out.println(choice);
                    break;
                case 2:
//                    finishGame();
                    System.out.println(choice);
                    break;
                case 3:
//                    updateScore();
                    System.out.println(choice);
                    break;
                case 4:
//                    getSummaryByTotalScore();
                    System.out.println(choice);
                    break;
                case 5:
                    System.exit(0);
            }
        }
    }
}
