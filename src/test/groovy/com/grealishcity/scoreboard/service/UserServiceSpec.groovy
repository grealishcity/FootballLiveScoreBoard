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

    def "should return team name"() {
        given:
        def teamName = "test"
        def scanner = new Scanner(teamName)
        userService = new UserService(scanner)

        when:
        def result = userService.getTeamName()

        then:
        result == teamName
    }
}
