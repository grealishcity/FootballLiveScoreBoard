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
}
