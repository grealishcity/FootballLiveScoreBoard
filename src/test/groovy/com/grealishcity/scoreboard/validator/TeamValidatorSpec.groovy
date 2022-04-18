package com.grealishcity.scoreboard.validator

import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

class TeamValidatorSpec extends Specification {

    @Shared
    def homeTeamName = "Poland"
    @Shared
    def awayTeamName = "Germany"
    @Shared
    def TOO_MANY_CHARACTERS_TEAM_NAME = 'AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA'

    @Subject
    def teamValidator = new TeamValidator();

    @Unroll
    def "should return #expectedResult when #desc"() {
        when:
        def result = teamValidator.test(homeTeam, awayTeam)

        then:
        result == expectedResult

        where:
        desc                                           | homeTeam                      | awayTeam                      || expectedResult
        'home team name is correct'                    | homeTeamName                  | awayTeamName                  || true
        'home team name is null'                       | null                          | awayTeamName                  || false
        'home team name is empty'                      | ''                            | awayTeamName                  || false
        'home team name has invalid characters'        | '%!%()'                       | awayTeamName                  || false
        'home team name has digits'                    | '321'                         | awayTeamName                  || false
        'home team name is too short'                  | 'aa'                          | awayTeamName                  || false
        'home team name is too long'                   | TOO_MANY_CHARACTERS_TEAM_NAME | awayTeamName                  || false
        'away team name is correct'                    | homeTeamName                  | awayTeamName                      || true
        'away team name is null'                       | homeTeamName                  | null                          || false
        'away team name is empty'                      | homeTeamName                  | ''                            || false
        'away team name has invalid characters'        | homeTeamName                  | '%!%()'                       || false
        'away team name has digits'                    | homeTeamName                  | '321'                         || false
        'away team name is too short'                  | homeTeamName                  | 'aa'                          || false
        'away team name is too long'                   | homeTeamName                  | TOO_MANY_CHARACTERS_TEAM_NAME || false
        'home team name is the same as away team name' | homeTeamName                  | homeTeamName                      || false
    }
}

