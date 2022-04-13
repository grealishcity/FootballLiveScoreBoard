package com.grealishcity.scoreboard.service

import com.grealishcity.scoreboard.dao.GameDao
import com.grealishcity.scoreboard.model.Team
import spock.lang.Specification
import spock.lang.Subject

class GameServiceSpec extends Specification {

    def gameDao = Mock(GameDao)

    @Subject
    def gameService = new GameService(gameDao)

    def "should create a new game"() {
        given:
        def homeTeamName = "Poland"
        def awayTeamName = "Germany"

        when:
        gameService.create(homeTeamName, awayTeamName)

        then:
        1 * gameDao.create(_ as Team, _ as Team) >> { args ->
            def homeTeam = args[0] as Team
            def awayTeam = args[1] as Team

            assert homeTeam.name == homeTeamName
            assert homeTeam.currentNumberOfGoals == 0
            assert awayTeam.name == awayTeamName
            assert awayTeam.currentNumberOfGoals == 0
        }
        noExceptionThrown()
    }
}
