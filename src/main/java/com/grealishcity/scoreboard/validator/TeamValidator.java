package com.grealishcity.scoreboard.validator;

import java.util.function.BiPredicate;

public class TeamValidator implements BiPredicate<String, String> {

    private static final String TEAM_NAME_PATTERN = "[a-zA-Z]{3,100}";

    @Override
    public boolean test(String homeTeamName, String awayTeamName) {
        return isTeamNameValid(homeTeamName) && isTeamNameValid(awayTeamName) && !homeTeamName.equalsIgnoreCase(awayTeamName);
    }

    private boolean isTeamNameValid(String teamName) {
        return teamName != null && teamName.matches(TEAM_NAME_PATTERN);
    }
}
