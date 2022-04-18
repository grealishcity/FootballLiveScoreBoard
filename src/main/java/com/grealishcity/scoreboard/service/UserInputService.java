package com.grealishcity.scoreboard.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.grealishcity.scoreboard.model.Team;
import com.grealishcity.scoreboard.validator.TeamValidator;

public class UserInputService {

    private final Scanner scanner;
    private final TeamValidator teamValidator;

    public UserInputService(Scanner scanner, TeamValidator teamValidator) {
        this.scanner = scanner;
        this.teamValidator = teamValidator;
    }

    public int getUserChoice() {
        int choice;

        try {
            System.out.print("Choose an option: ");
            choice = Integer.parseInt(scanner.nextLine());
            if (choice <= 0) {
                choice = 0;
            }
        } catch (NumberFormatException e) {
            choice = 0;
        }

        return choice;
    }

    public List<Team> getTeams() {
        List<Team> teamsNames = new ArrayList<>();

        System.out.print("Give home team name: ");
        String homeTeamName = scanner.nextLine();
        System.out.print("Give away team name: ");
        String awayTeamName = scanner.nextLine();

        if (!teamValidator.test(homeTeamName, awayTeamName)) {
            throw new IllegalArgumentException("Invalid home team or away team name given!");
        }

        teamsNames.add(new Team(homeTeamName));
        teamsNames.add(new Team(awayTeamName));

        return teamsNames;
    }

    public List<Team> getTeamsWithGoals() {
        List<Team> teams = getTeams();

        try {
            System.out.print("Give home team goals: ");
            int homeTeamGoals = Integer.parseInt(scanner.nextLine());

            System.out.print("Give away team goals: ");
            int awayTeamGoals = Integer.parseInt(scanner.nextLine());

            if (homeTeamGoals >= 0 && awayTeamGoals >= 0) {
                teams.get(0).setCurrentNumberOfGoals(homeTeamGoals);
                teams.get(1).setCurrentNumberOfGoals(awayTeamGoals);
            } else {
                System.out.println("Number of goals cannot be negative number!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Incorrect number of goals given!");
        }

        return teams;
    }
}
