package com.grealishcity.scoreboard.validator

import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

class TeamValidatorSpec extends Specification {

    @Shared
    def TOO_MANY_CHARACTERS_TEAM_NAME = 'AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA'

    @Subject
    def teamValidator = new TeamValidator();

    @Unroll
    def "should return #expectedResult when #desc"() {
        when:
        def result = teamValidator.test(teamName)

        then:
        result == expectedResult

        where:
        desc                               | teamName                      || expectedResult
        'team name is correct'             | 'Poland'                      || true
        'team name is null'                | null                          || false
        'team name is empty'               | ''                            || false
        'team name has invalid characters' | '%!%()'                       || false
        'team name has digits'             | '321'                         || false
        'team name is too short'           | 'aa'                          || false
        'team name is too long'            | TOO_MANY_CHARACTERS_TEAM_NAME || false
    }
}

