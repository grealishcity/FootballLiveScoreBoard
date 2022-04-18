package com.grealishcity.scoreboard.service

import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

class UserServiceSpec extends Specification {

    @Subject
    def userService

    @Unroll
    def "should return #expected when input is #desc"() {
        given:
        def scanner = new Scanner(input)
        userService = new UserService(scanner)

        when:
        def result = userService.getUserChoice()

        then:
        result == expected

        where:
        desc               | input   || expected
        'correct number'   | '1'     || 1
        'incorrect number' | '10'    || 10
        'not number'       | '!@#$%' || 0
    }

    def "should return teams names"() {
        given:
        def homeTeamName = "Poland"
        def awayTeamName = "Austria"
        def scanner = new Scanner(homeTeamName + "\n" + awayTeamName)
        userService = new UserService(scanner)

        when:
        def teams = userService.getTeamsNames()

        then:
        teams.containsAll([homeTeamName, awayTeamName])
    }

    def "should return teams with goals"() {
        given:
        def homeTeamName = "Poland"
        def awayTeamName = "Austria"
        def homeTeamGoals = "5"
        def awayTeamGoals = "6"
        def scanner = new Scanner(homeTeamName + "\n" + awayTeamName + "\n" + homeTeamGoals + "\n" + awayTeamGoals)
        userService = new UserService(scanner)

        when:
        def teams = userService.getTeamsNamesWithGoals()

        then:
        teams.containsAll([homeTeamGoals, awayTeamGoals])
    }
}


