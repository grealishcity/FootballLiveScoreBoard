package com.grealishcity.scoreboard.validator

import com.grealishcity.scoreboard.dao.GameDao
import com.grealishcity.scoreboard.model.Team
import spock.lang.Specification
import spock.lang.Subject

class GameValidatorSpec extends Specification {

    def gameDao = Mock(GameDao)

    @Subject
    def gameValidator = new GameValidator(gameDao)

    def "should return true when team is not already on board"() {
        given:
        def team = new Team("Poland")

        when:
        def result = gameValidator.test(team)

        then:
        result
    }

    def "should return false when team is already on board"() {
        given:
        def team = new Team("Poland")

        when:
        def result = gameValidator.test(team)

        then:
        !result
    }

    def "should return true when game exists"() {
        given:
        def homeTeam = new Team("Poland")
        def awayTeam = new Team("Germany")

        when:
        def result = gameValidator.checkGameExists(homeTeam, awayTeam)

        then:
        result
    }

    def "should return false when game not exist"() {
        given:
        def homeTeam = new Team("Poland")
        def awayTeam = new Team("Germany")

        when:
        def result = gameValidator.checkGameExists(homeTeam, awayTeam)

        then:
        !result
    }

}
