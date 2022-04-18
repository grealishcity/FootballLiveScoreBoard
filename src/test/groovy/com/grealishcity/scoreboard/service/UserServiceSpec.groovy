package com.grealishcity.scoreboard.service

import spock.lang.Specification

class UserServiceSpec extends Specification {

    def scanner = Mock(Scanner)

    def userService = new UserService(scanner)

    def "should return user choice when input is correct"() {
        given:
        def input = "1"

        when:
        def choice = userService.getUserChoice()

        then:
        choice == Integer.valueOf(input)
    }
}
