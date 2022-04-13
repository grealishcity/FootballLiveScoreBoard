package com.grealishcity.scoreboard.service;

import com.grealishcity.scoreboard.model.Team;

public class GameService {

    public void create(String homeTeamName, String awayTeamName) {
        //TODO: Teams validation, game validation

        Team homeTeam = new Team(homeTeamName);
        Team awayTeam = new Team(awayTeamName);


    }
}
