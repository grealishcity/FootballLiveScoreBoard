package com.grealishcity.scoreboard.service

import com.grealishcity.scoreboard.dao.GameDao
import com.grealishcity.scoreboard.exception.ObjectNotFoundException
import com.grealishcity.scoreboard.model.Team
import com.grealishcity.scoreboard.validator.GameValidator
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject

class GameServiceSpec extends Specification {

    @Shared
    def homeTeamName = "Poland"
    @Shared
    def awayTeamName = "Germany"
    @Shared
    def homeTeam = new Team(homeTeamName)
    @Shared
    def awayTeam = new Team(awayTeamName)

    def gameDao = Mock(GameDao)
    def gameValidator = Mock(GameValidator)

    @Subject
    def gameService = new GameService(gameDao, gameValidator)

    def "should create a new game"() {
        when:
        gameService.create([homeTeam, awayTeam])

        then:
        2 * gameValidator.checkTeamIsAlreadyOnBoard(_ as Team) >> false
        1 * gameDao.create(homeTeam, awayTeam)
    }

    def "should throw exception when given home team is already on board"() {
        given:
        def expectedExceptionMessage = "Given home team: " + homeTeamName + " is already on board!"

        when:
        gameService.create([homeTeam, awayTeam])

        then:
        1 * gameValidator.checkTeamIsAlreadyOnBoard(homeTeam) >> true
        def e = thrown(IllegalStateException)
        e.message == expectedExceptionMessage
    }

    def "should throw exception when given away team is already on board"() {
        given:
        def expectedExceptionMessage = "Given away team: " + awayTeamName + " is already on board!"

        when:
        gameService.create([homeTeam, awayTeam])

        then:
        1 * gameValidator.checkTeamIsAlreadyOnBoard(homeTeam) >> false
        1 * gameValidator.checkTeamIsAlreadyOnBoard(awayTeam) >> true
        def e = thrown(IllegalStateException)
        e.message == expectedExceptionMessage
    }

    def "should finish game"() {
        when:
        gameService.finish([homeTeam, awayTeam])

        then:
        1 * gameValidator.checkGameExists(homeTeam, awayTeam) >> true
        1 * gameDao.finish(homeTeam, awayTeam)
    }

    def "should throw exception when game not exists"() {
        given:
        def expectedExceptionMessage = "Game not found for given home team: " + homeTeam.getName() + " and away team: " + awayTeam.getName()

        when:
        gameService.finish([homeTeam, awayTeam])

        then:
        1 * gameValidator.checkGameExists(homeTeam, awayTeam) >> false
        def e = thrown(ObjectNotFoundException)
        e.message == expectedExceptionMessage
    }

    def "should update game score"() {
        given:
        def homeTeamName = "Poland"
        def awayTeamName = "Germany"
        def homeTeamGoals = 5
        def awayTeamGoals = 4
        def givenHomeTeam = new Team(homeTeamName, homeTeamGoals)
        def givenAwayTeam = new Team(awayTeamName, awayTeamGoals)

        when:
        gameService.update(givenHomeTeam, givenAwayTeam)

        then:
        1 * gameValidator.checkGameExists(_ as Team, _ as Team) >> { args ->
            def homeTeam = args[0] as Team
            def awayTeam = args[1] as Team

            assert homeTeam.name == homeTeamName
            assert homeTeam.currentNumberOfGoals == 5
            assert awayTeam.name == awayTeamName
            assert awayTeam.currentNumberOfGoals == 4
        } >> true
        1 * gameDao.update(_ as Team, _ as Team) >> { args ->
            def homeTeam = args[0] as Team
            def awayTeam = args[1] as Team

            assert homeTeam.name == homeTeamName
            assert homeTeam.currentNumberOfGoals == 5
            assert awayTeam.name == awayTeamName
            assert awayTeam.currentNumberOfGoals == 4
        }
    }

    def "should call game dao to get summary by total score"() {
        when:
        gameService.getSummaryByTotalScore()

        then:
        1 * gameDao.getSummaryByTotalScore()
    }

}
