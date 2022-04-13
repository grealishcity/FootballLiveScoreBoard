package com.grealishcity.scoreboard.service

import com.grealishcity.scoreboard.dao.GameDao
import spock.lang.Specification
import spock.lang.Subject

class GameServiceSpec extends Specification {

    def gameDao = Mock(GameDao)

    @Subject
    def gameService = new GameService(gameDao)

    def "should create a new game"() {
        given:
        def homeTeam = "Poland"
        def awayTeam = "Germany"

        when:
        gameService.create(homeTeam, awayTeam)

        then:
        noExceptionThrown()
    }
}
