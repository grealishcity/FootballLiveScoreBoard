package com.grealishcity.scoreboard.model;

public class Team {

    private String name;
    private Integer currentNumberOfGoals;

    public Team(String name) {
        this.name = name;
        this.currentNumberOfGoals = 0;
    }
}
