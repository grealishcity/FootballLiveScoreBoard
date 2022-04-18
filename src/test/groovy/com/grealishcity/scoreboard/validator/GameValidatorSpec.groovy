package com.grealishcity.scoreboard.validator

import com.grealishcity.scoreboard.dao.GameDao
import com.grealishcity.scoreboard.model.Game
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
        def result = gameValidator.checkTeamIsAlreadyOnBoard(team)

        then:
        1 * gameDao.getGames() >> [new Game(team, team)]
        result
    }

    def "should return false when team is already on board"() {
        given:
        def team = new Team("Poland")
        def notExistingTeam = new Team("test")

        when:
        def result = gameValidator.checkTeamIsAlreadyOnBoard(team)

        then:
        1 * gameDao.getGames() >> [new Game(notExistingTeam, notExistingTeam)]
        !result
    }

    def "should return true when game exists"() {
        given:
        def homeTeam = new Team("Poland")
        def awayTeam = new Team("Germany")

        when:
        def result = gameValidator.checkGameExists(homeTeam, awayTeam)

        then:
        1 * gameDao.getGames() >> [new Game(homeTeam, awayTeam)]
        result
    }

    def "should return false when game not exist"() {
        given:
        def homeTeam = new Team("Poland")
        def awayTeam = new Team("Germany")

        when:
        def result = gameValidator.checkGameExists(homeTeam, awayTeam)

        then:
        1 * gameDao.getGames() >> []
        !result
    }

}
