package com.grealishcity.scoreboard.validator;

import java.util.function.Predicate;

public class TeamValidator implements Predicate<String> {

    private static final String TEAM_NAME_PATTERN = "[a-zA-Z]{3,100}";

    @Override
    public boolean test(String teamName) {
        return teamName != null && teamName.matches(TEAM_NAME_PATTERN);
    }
}
