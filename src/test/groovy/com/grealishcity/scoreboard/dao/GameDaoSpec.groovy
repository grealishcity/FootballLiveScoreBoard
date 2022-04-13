package com.grealishcity.scoreboard.dao

import com.grealishcity.scoreboard.model.Game
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
        gameDao.games.size() > 0
        gameDao.games.find {it.getHomeTeam() == (homeTeam) && it.getAwayTeam() == (awayTeam)}
    }
}
