package com.grealishcity.scoreboard.service;

import java.util.ArrayList;
import java.util.List;
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

    public List<String> getTeamsNames() {
        List<String> teamsNames = new ArrayList<>();
        System.out.print("Give home team name: ");
        teamsNames.add(scanner.nextLine());
        System.out.print("Give away team name: ");
        teamsNames.add(scanner.nextLine());

        return teamsNames;
    }
}
