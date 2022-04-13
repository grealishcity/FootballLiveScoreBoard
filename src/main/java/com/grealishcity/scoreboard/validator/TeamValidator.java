package com.grealishcity.scoreboard.validator;

import java.util.function.Predicate;

public class TeamValidator implements Predicate<String> {

    @Override
    public boolean test(String teamName) {
        boolean isCorrect = true;
        String teamNamePattern = "[a-zA-Z]+";

        if (teamName == null) {
            isCorrect = false;
        } else {
            if (teamName.isEmpty()) {
                isCorrect = false;
            }

            if (!teamName.matches(teamNamePattern)) {
                isCorrect = false;
            }

            if (teamName.length() < 3 || teamName.length() > 100) {
                isCorrect = false;
            }
        }

        return isCorrect;
    }
}
