package com.grealishcity.scoreboard.model;

import java.util.Objects;

public class Team {

    private String name;
    private Integer currentNumberOfGoals;

    public Team(String name) {
        this.name = name;
        this.currentNumberOfGoals = 0;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(name, team.name) && Objects.equals(currentNumberOfGoals, team.currentNumberOfGoals);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, currentNumberOfGoals);
    }
}
