package com.grealishcity.scoreboard.validator

import spock.lang.Specification
import spock.lang.Subject

class GameValidatorSpec extends Specification {

    @Subject
    def gameValidator = new GameValidator()

    def "should return true when game not exists"() {
        given:

        when:

        then:
        noExceptionThrown()
    }

    def "should return false when game already exists"() {
        given:

        when:

        then:
        noExceptionThrown()
    }

    def "should return false when team is already on board"() {
        given:

        when:

        then:
        noExceptionThrown()
    }

}
