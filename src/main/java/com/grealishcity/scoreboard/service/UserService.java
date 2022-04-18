package com.grealishcity.scoreboard.service;

import java.util.Scanner;

public class UserService {

    private final Scanner scanner;

    public UserService(Scanner scanner) {
        this.scanner = scanner;
    }

    public int getUserChoice() {
        int choice;

        try {
            System.out.print("Choose an option: ");
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            choice = 0;
        }

        return choice;
    }
}
