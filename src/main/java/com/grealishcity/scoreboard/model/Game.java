package com.grealishcity.scoreboard.model;

import java.time.LocalDateTime;

public class Game {

    private Team homeTeam;
    private Team awayTeam;

    private LocalDateTime created;

    public Game(Team homeTeam, Team awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.created = LocalDateTime.now();
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public LocalDateTime getCreated() {
        return created;
    }

}
