package com.grealishcity.scoreboard.dao

import com.grealishcity.scoreboard.exception.ObjectNotFoundException
import com.grealishcity.scoreboard.model.Team
import spock.lang.Specification
import spock.lang.Subject

class GameDaoSpec extends Specification {

    @Subject
    def gameDao = new GameDao()

    def "should add new match to game list"() {
        given:
        def homeTeam = new Team("Poland")
        def awayTeam = new Team("Germany")

        when:
        gameDao.create(homeTeam, awayTeam);

        then:
        !gameDao.games.isEmpty()
        gameDao.games.find {it.getHomeTeam() == (homeTeam) && it.getAwayTeam() == (awayTeam)}
    }

    def "should remove game from list"() {
        given:
        def homeTeam = new Team("Poland")
        def awayTeam = new Team("Germany")

        gameDao.create(homeTeam, awayTeam)

        when:
        gameDao.finish(homeTeam, awayTeam)

        then:
        gameDao.games.isEmpty()
        !gameDao.games.find {it.getHomeTeam() == (homeTeam) && it.getAwayTeam() == (awayTeam)}
    }

    def "should throw exception when game not exists"() {
        given:
        def homeTeam = new Team("Poland")
        def awayTeam = new Team("Germany")

        when:
        gameDao.finish(homeTeam, awayTeam)

        then:
        def e = thrown(ObjectNotFoundException)
        e.message == "Game not found"
    }
}
