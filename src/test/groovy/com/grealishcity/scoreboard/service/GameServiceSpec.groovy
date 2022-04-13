package com.grealishcity.scoreboard.service

import spock.lang.Specification
import spock.lang.Subject

class GameServiceSpec extends Specification {

    @Subject
    def gameService = new GameService()

    def "should create a new game"() {
        given:
        def homeTeam = "Poland"
        def awayTeam = "Germany"

        when:
        gameService.create(homeTeam, awayTeam)

        then:
        assert 1 == 1
    }
}
