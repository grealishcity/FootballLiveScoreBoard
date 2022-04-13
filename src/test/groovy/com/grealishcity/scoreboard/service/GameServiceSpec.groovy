package com.grealishcity.scoreboard.service

import com.grealishcity.scoreboard.dao.GameDao
import com.grealishcity.scoreboard.model.Team
import com.grealishcity.scoreboard.validator.GameValidator
import com.grealishcity.scoreboard.validator.TeamValidator
import spock.lang.Specification
import spock.lang.Subject

class GameServiceSpec extends Specification {

    def gameDao = Mock(GameDao)
    def teamValidator = Mock(TeamValidator)
    def gameValidator = Mock(GameValidator)

    @Subject
    def gameService = new GameService(gameDao, teamValidator, gameValidator)

    def "should create a new game"() {
        given:
        def homeTeamName = "Poland"
        def awayTeamName = "Germany"

        when:
        gameService.create(homeTeamName, awayTeamName)

        then:
        2 * teamValidator.test(_ as String) >> true
        1 * gameDao.create(_ as Team, _ as Team) >> { args ->
            def homeTeam = args[0] as Team
            def awayTeam = args[1] as Team

            assert homeTeam.name == homeTeamName
            assert homeTeam.currentNumberOfGoals == 0
            assert awayTeam.name == awayTeamName
            assert awayTeam.currentNumberOfGoals == 0
        }
        2 * gameValidator.test(_ as Team) >> false
    }

    def "should throw exception when given home team is not correct"() {
        given:
        def homeTeamName = "aa"
        def awayTeamName = "Germany"

        when:
        gameService.create(homeTeamName, awayTeamName)

        then:
        1 * teamValidator.test(homeTeamName) >> false
        def e = thrown(IllegalArgumentException)
        e.message == "Illegal home team name given: " + homeTeamName
    }

    def "should throw exception when given away team is not correct"() {
        given:
        def homeTeamName = "Poland"
        def awayTeamName = "aa"

        when:
        gameService.create(homeTeamName, awayTeamName)

        then:
        1 * teamValidator.test(homeTeamName) >> true
        1 * teamValidator.test(awayTeamName) >> false
        def e = thrown(IllegalArgumentException)
        e.message == "Illegal away team name given: " + awayTeamName
    }

    def "should throw exception when home team is already on board"() {
        given:
        def homeTeamName = "Poland"
        def awayTeamName = "Germany"

        when:
        gameService.create(homeTeamName, awayTeamName)

        then:
        2 * teamValidator.test(_ as String) >> true
        1 * gameValidator.test(_ as Team) >> true
        def e = thrown(IllegalStateException)
        e.message == "Team is already on board: " + homeTeamName
    }

    def "should throw exception when away team is already on board"() {
        given:
        def homeTeamName = "Poland"
        def awayTeamName = "Germany"

        when:
        gameService.create(homeTeamName, awayTeamName)

        then:
        2 * teamValidator.test(_ as String) >> true
        1 * gameValidator.test(_ as Team) >> false
        1 * gameValidator.test(_ as Team) >> true
        def e = thrown(IllegalStateException)
        e.message == "Team is already on board: " + awayTeamName
    }

    def "should finish game"() {
        given:
        def homeTeamName = "Poland"
        def awayTeamName = "Germany"

        when:
        gameService.finish(homeTeamName, awayTeamName)

        then:
        2 * teamValidator.test(_ as String) >> true
        1 * gameValidator.checkGameExists(_ as Team, _ as Team) >> { args ->
            def homeTeam = args[0] as Team
            def awayTeam = args[1] as Team

            assert homeTeam.name == homeTeamName
            assert homeTeam.currentNumberOfGoals == 0
            assert awayTeam.name == awayTeamName
            assert awayTeam.currentNumberOfGoals == 0
        } >> true
        1 * gameDao.finish(_ as Team, _ as Team) >> { args ->
            def homeTeam = args[0] as Team
            def awayTeam = args[1] as Team

            assert homeTeam.name == homeTeamName
            assert homeTeam.currentNumberOfGoals == 0
            assert awayTeam.name == awayTeamName
            assert awayTeam.currentNumberOfGoals == 0
        }
    }

//    def "should throw exception when game not exists"() {
//        given:
//
//        when:
//
//        then:
//        noExceptionThrown()
//    }

}
