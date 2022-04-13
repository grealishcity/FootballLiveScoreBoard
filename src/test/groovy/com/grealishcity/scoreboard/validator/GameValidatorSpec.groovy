package com.grealishcity.scoreboard.validator

import spock.lang.Specification
import spock.lang.Subject

class GameValidatorSpec extends Specification {

    @Subject
    def gameValidator = new GameValidator()

    def "should return true when game not exists"() {
        given:
        def homeTeamName = "Poland"
        def awayTeamName = "Germany"

        when:
        def result = gameValidator.test(homeTeamName, awayTeamName)

        then:
        result
    }

    def "should return false when game already exists"() {
        given:
        def homeTeamName = "Poland"
        def awayTeamName = "Germany"

        when:
        def result = gameValidator.test(homeTeamName, awayTeamName)

        then:
        !result
    }

    def "should return false when team is already on board"() {
        given:
        def homeTeamName = "Poland"
        def awayTeamName = "Germany"

        when:
        def result = gameValidator.test(homeTeamName, awayTeamName)

        then:
        !result
    }

}
