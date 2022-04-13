package com.grealishcity.scoreboard.validator;

import java.util.function.Predicate;

public class TeamValidator implements Predicate<String> {

    @Override
    public boolean test(String teamName) {
        return false;
    }
}
