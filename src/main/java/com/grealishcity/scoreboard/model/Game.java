package com.grealishcity.scoreboard.model;


public class Game {

    private Team homeTeam;
    private Team awayTeam;
    private Score currentScore;

    public Game(Team homeTeam, Team awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.currentScore = new Score(homeTeam.getCurrentNumberOfGoals(), awayTeam.getCurrentNumberOfGoals());
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public Score getCurrentScore() {
        return currentScore;
    }
}
