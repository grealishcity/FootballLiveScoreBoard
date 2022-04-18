package com.grealishcity.scoreboard.service


import com.grealishcity.scoreboard.validator.TeamValidator
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

class UserServiceSpec extends Specification {

    @Shared
    def homeTeamName = "Poland"
    @Shared
    def awayTeamName = "Austria"

    def teamValidator = Mock(TeamValidator)

    @Subject
    def userService

    @Unroll
    def "should return #expected when input is #desc"() {
        given:
        def scanner = new Scanner(input)
        userService = new UserService(scanner, teamValidator)

        when:
        def result = userService.getUserChoice()

        then:
        result == expected

        where:
        desc               | input   || expected
        'correct number'   | '1'     || 1
        'incorrect number' | '-10'   || 0
        'incorrect number' | '10'    || 10
        'incorrect number' | '0'     || 0
        'not number'       | '!@#$%' || 0
    }

    def "should return teams"() {
        given:
        def scanner = new Scanner(homeTeamName + "\n" + awayTeamName)
        userService = new UserService(scanner, teamValidator)

        when:
        def teams = userService.getTeams()

        then:
        2 * teamValidator.test(_ as String) >> true
        teams.get(0).name == homeTeamName && teams.get(1).name == awayTeamName
    }

    def "should throw exception when home team name is not valid"() {
        given:
        def expectedExceptionMessage = "Invalid home team name given: " + homeTeamName
        def scanner = new Scanner(homeTeamName + "\n" + awayTeamName)
        userService = new UserService(scanner, teamValidator)

        when:
        userService.getTeams()

        then:
        1 * teamValidator.test(homeTeamName) >> false
        def e = thrown(IllegalArgumentException)
        e.message == expectedExceptionMessage
    }

    def "should throw exception when away team name is not valid"() {
        given:
        def expectedExceptionMessage = "Invalid away team name given: " + awayTeamName
        def scanner = new Scanner(homeTeamName + "\n" + awayTeamName)
        userService = new UserService(scanner, teamValidator)

        when:
        userService.getTeams()

        then:
        1 * teamValidator.test(homeTeamName) >> true
        1 * teamValidator.test(awayTeamName) >> false
        def e = thrown(IllegalArgumentException)
        e.message == expectedExceptionMessage
    }

    def "should return teams with goals"() {
        given:
        def homeTeamGoals = "5"
        def awayTeamGoals = "6"
        def scanner = new Scanner(homeTeamName + "\n" + awayTeamName + "\n" + homeTeamGoals + "\n" + awayTeamGoals)
        userService = new UserService(scanner, teamValidator)

        when:
        def teams = userService.getTeamsWithGoals()

        then:
        2 * teamValidator.test(_ as String) >> true
        teams.get(0).name == homeTeamName && teams.get(0).getCurrentNumberOfGoals() == Integer.valueOf(homeTeamGoals)
        teams.get(1).name == awayTeamName && teams.get(1).getCurrentNumberOfGoals() == Integer.valueOf(awayTeamGoals)
    }
}


