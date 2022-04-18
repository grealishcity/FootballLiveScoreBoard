package com.grealishcity.scoreboard.service

import spock.lang.Specification
import spock.lang.Subject

class UserServiceSpec extends Specification {

    @Subject
    def userService

    def "should return user choice when input is correct"() {
        given:
        def input = "1"
        def scanner = new Scanner(input)
        userService = new UserService(scanner)

        when:
        def choice = userService.getUserChoice()

        then:
        choice == Integer.valueOf(input)
    }
}
