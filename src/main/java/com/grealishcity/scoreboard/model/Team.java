package com.grealishcity.scoreboard.model;

public class Team {

    private String name;
    private Integer currentNumberOfGoals;

    public Team(String name) {
        this.name = name;
        this.currentNumberOfGoals = 0;
    }

    public Team(String name, Integer currentNumberOfGoals) {
        this.name = name;
        this.currentNumberOfGoals = currentNumberOfGoals;
    }

    public String getName() {
        return name;
    }

    public Integer getCurrentNumberOfGoals() {
        return currentNumberOfGoals;
    }

    public void setCurrentNumberOfGoals(Integer currentNumberOfGoals) {
        this.currentNumberOfGoals = currentNumberOfGoals;
    }
}
