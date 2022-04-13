package com.grealishcity.scoreboard.validator

import spock.lang.Specification
import spock.lang.Subject

class TeamValidatorSpec extends Specification {

    @Subject
    def teamValidator = new TeamValidator();

    def "should return true when team name is correct"() {
        given:
        def teamName = "test"

        when:
        def result = teamValidator.test(teamName);

        then:
        result
    }

    def "should return false when team name is null"() {
        given:
        def teamName = "test"

        when:
        def result = teamValidator.test(teamName);

        then:
        result
    }

    def "should return false when team name is empty"() {
        given:
        def teamName = "test"

        when:
        def result = teamValidator.test(teamName);

        then:
        result
    }

    def "should return false when team name contains illegal characters"() {
        given:
        def teamName = "test"

        when:
        def result = teamValidator.test(teamName);

        then:
        result
    }

    def "should return false when team name length is greater than 100"() {
        given:
        def teamName = "test"

        when:
        def result = teamValidator.test(teamName);

        then:
        result
    }

    def "should return false when team name length is lesser than 5"() {
        given:
        def teamName = "test"

        when:
        def result = teamValidator.test(teamName);

        then:
        result
    }
}

