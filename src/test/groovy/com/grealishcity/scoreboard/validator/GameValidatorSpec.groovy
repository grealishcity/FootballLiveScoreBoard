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
        def teamName = new Team("Poland")

        when:
        def result = gameValidator.test(teamName)

        then:
        result
    }

    def "should return false when team is already on board"() {
        given:
        def teamName = new Team("Poland")

        when:
        def result = gameValidator.test(teamName)

        then:
        !result
    }

}
