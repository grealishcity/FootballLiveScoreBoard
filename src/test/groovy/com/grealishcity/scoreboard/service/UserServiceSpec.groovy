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
        def choice = userService.getUserChoice()

        then:
        choice == expected

        where:
        desc               | input   || expected
        'correct number'   | '1'     || 1
        'incorrect number' | '10'    || 10
        'not number'       | '!@#$%' || 0
    }
}
