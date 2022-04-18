package com.grealishcity.scoreboard.dao

import com.grealishcity.scoreboard.exception.ObjectNotFoundException
import com.grealishcity.scoreboard.model.Team
import org.testng.annotations.AfterMethod
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject

class GameDaoSpec extends Specification {

    @Shared
    def homeTeamName = "Poland"
    @Shared
    def awayTeamName = "Germany"
    @Shared
    def homeTeam = new Team(homeTeamName)
    @Shared
    def awayTeam = new Team(awayTeamName)

    @Subject
    def gameDao = new GameDao()

    @AfterMethod
    void afterMethod() {
        gameDao.games.clear()
    }

    def "should add new match to game list"() {
        when:
        gameDao.create(homeTeam, awayTeam)

        then:
        !gameDao.games.isEmpty()
        gameDao.games.find {it.getHomeTeam() == homeTeam && it.getAwayTeam() == awayTeam }
    }

    def "should remove game from list"() {
        given:
        gameDao.create(homeTeam, awayTeam)

        when:
        gameDao.finish(homeTeam, awayTeam)

        then:
        gameDao.games.isEmpty()
        !gameDao.games.find {it.getHomeTeam() == homeTeam && it.getAwayTeam() == awayTeam }
    }

    def "should throw exception when game not exists"() {
        given:
        def notExistingTeam = new Team("test")
        def expectedExceptionMessage = "Game not found for given home team: " + notExistingTeam.getName() + " and away team: " + notExistingTeam.getName()

        gameDao.create(homeTeam, awayTeam)

        when:
        gameDao.finish(notExistingTeam, notExistingTeam)

        then:
        def e = thrown(ObjectNotFoundException)
        e.message == expectedExceptionMessage
    }

    def "should update game score"() {
        given:
        def updatedHomeTeam = new Team(homeTeamName)
        def updatedAwayTeam = new Team(awayTeamName)
        updatedHomeTeam.currentNumberOfGoals = 5
        updatedAwayTeam.currentNumberOfGoals = 4

        gameDao.create(homeTeam, awayTeam)

        when:
        gameDao.update(updatedHomeTeam, updatedAwayTeam)

        then:
        gameDao.games.find { it.getHomeTeam() == updatedHomeTeam && it.getAwayTeam() == updatedAwayTeam }
    }

    def "should return summary of games by total score"() {
        given:
        def homeTeamsNames = ["Poland", "Portugal", "Austria"]
        def awayTeamsNames = ["Germany", "Russia", "France"]
        def homeTeams = [new Team(homeTeamsNames.get(0)), new Team(homeTeamsNames.get(1)), new Team(homeTeamsNames.get(2))]
        def awayTeams = [new Team(awayTeamsNames.get(0)), new Team(awayTeamsNames.get(1)), new Team(awayTeamsNames.get(2))]

        createGames(homeTeams, awayTeams)
        updateGames(homeTeams, awayTeams)

        when:
        def gamesByTotalScore = gameDao.getSummaryByTotalScore()
        def totalGameScoreList = getTotalGameScoreList(gamesByTotalScore)

        then:
        totalGameScoreList.get(0) >= totalGameScoreList.get(1) && totalGameScoreList.get(1) >= totalGameScoreList.get(2)
    }

    def "should sort games by creation date when number of goals is equal"() {
        given:
        def firstHomeTeam = new Team("Poland")
        def firstAwayTeam = new Team("Portugal")
        def secondHomeTeam = new Team("Germany")
        def secondAwayTeam = new Team("Austria")
        def homeTeamGoals = 10
        def awayTeamGoals = 5

        gameDao.create(secondHomeTeam, secondAwayTeam)
        gameDao.create(firstHomeTeam, firstAwayTeam)

        firstHomeTeam.currentNumberOfGoals = homeTeamGoals
        firstAwayTeam.currentNumberOfGoals = awayTeamGoals
        secondHomeTeam.currentNumberOfGoals = homeTeamGoals
        secondAwayTeam.currentNumberOfGoals = awayTeamGoals

        gameDao.update(firstHomeTeam, firstAwayTeam)
        gameDao.update(secondHomeTeam, secondAwayTeam)

        when:
        def gamesByTotalScore = gameDao.getSummaryByTotalScore()
        def totalGameScoreList = getTotalGameScoreList(gamesByTotalScore)
        def firstGame = gamesByTotalScore.get(0)
        def secondGame = gamesByTotalScore.get(1)

        then:
        totalGameScoreList.get(0) == totalGameScoreList.get(1)
        firstGame.homeTeam == secondHomeTeam && firstGame.awayTeam == secondAwayTeam
        secondGame.homeTeam == firstHomeTeam && secondGame.awayTeam == firstAwayTeam
    }

    def createGames(homeTeams, awayTeams) {
        for (int i = 0; i < 3; i++) {
            def homeTeam = homeTeams.get(i)
            def awayTeam = awayTeams.get(i)

            gameDao.create(homeTeam as Team, awayTeam as Team)
        }
    }

    def updateGames(homeTeams, awayTeams) {
        for (int i = 0; i < 3; i++) {
            def homeTeam = homeTeams.get(i)
            def awayTeam = awayTeams.get(i)

            homeTeam.currentNumberOfGoals = generateRandomInt()
            awayTeam.currentNumberOfGoals = generateRandomInt()

            gameDao.update(homeTeam as Team, awayTeam as Team)
        }
    }

    def generateRandomInt() {
        def random = new Random()
        def range = 10

        random.nextInt(range)
    }

    def getTotalGameScoreList(gamesByTotalScore) {
        def totalGamesScores = []
        gamesByTotalScore.each { game ->
            totalGamesScores.add(calculateTotalNumberOfGoalsInGame(game))
        }

        totalGamesScores
    }

    def calculateTotalNumberOfGoalsInGame(game) {
        game.getHomeTeam().getCurrentNumberOfGoals() + game.getAwayTeam().getCurrentNumberOfGoals()
    }
}
