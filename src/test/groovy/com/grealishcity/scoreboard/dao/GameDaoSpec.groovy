package com.grealishcity.scoreboard.dao

import com.grealishcity.scoreboard.exception.ObjectNotFoundException
import com.grealishcity.scoreboard.model.Team
import org.testng.annotations.AfterMethod
import spock.lang.Specification
import spock.lang.Subject

class GameDaoSpec extends Specification {

    @Subject
    def gameDao = new GameDao()

    @AfterMethod
    void afterMethod() {
        gameDao.games.clear()
    }

    def "should add new match to game list"() {
        given:
        def homeTeam = new Team("Poland")
        def awayTeam = new Team("Germany")

        when:
        gameDao.create(homeTeam, awayTeam)

        then:
        !gameDao.games.isEmpty()
        gameDao.games.find {it.getHomeTeam() == homeTeam && it.getAwayTeam() == awayTeam }
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
        !gameDao.games.find {it.getHomeTeam() == homeTeam && it.getAwayTeam() == awayTeam }
    }

    def "should throw exception when game not exists"() {
        given:
        def homeTeam = new Team("Poland")
        def awayTeam = new Team("Germany")
        def notExistingTeam = new Team("test")

        gameDao.create(homeTeam, awayTeam)

        when:
        gameDao.finish(notExistingTeam, notExistingTeam)

        then:
        def e = thrown(ObjectNotFoundException)
        e.message == "Game not found for given home team: " + notExistingTeam.getName() + " and away team: " + notExistingTeam.getName()
    }

    def "should update game score"() {
        given:
        def homeTeamName = "Poland"
        def awayTeamName = "Germany"
        def homeTeam = new Team(homeTeamName)
        def awayTeam = new Team(awayTeamName)
        def updatedHomeTeam = new Team(homeTeamName, 5)
        def updatedAwayTeam = new Team(awayTeamName, 3)

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
        def homeTeams = [
                new Team(homeTeamsNames.get(0)),
                new Team(homeTeamsNames.get(1)),
                new Team(homeTeamsNames.get(2))
        ]
        def awayTeams = [
                new Team(awayTeamsNames.get(0)),
                new Team(awayTeamsNames.get(1)),
                new Team(awayTeamsNames.get(2))
        ]

        createGames(homeTeams, awayTeams)
        updateGames(homeTeams, awayTeams)

        when:
        def gamesByTotalScore = gameDao.getSummaryByTotalScore()
        def totalGameScoreList = getTotalGameScoreList(gamesByTotalScore)

        then:
        totalGameScoreList.get(0) >= totalGameScoreList.get(1) && totalGameScoreList.get(1) >= totalGameScoreList.get(2)
    }

//    def "should sort games by creation date when number of goals is equal"() {
//        given:
//        def firstHomeTeam = new Team("Poland")
//        def secondHomeTeam = new Team("Germany")
//        def firstAwayTeam = new Team("Portugal")
//        def secondAwayTeam = new Team("Austria")
//
//        gameDao.create(firstHomeTeam, firstAwayTeam)
//        gameDao.create(secondHomeTeam, secondAwayTeam)
//
//        firstHomeTeam.currentNumberOfGoals = 10
//        firstAwayTeam.currentNumberOfGoals = 5
//        secondHomeTeam.currentNumberOfGoals = 5
//        secondAwayTeam.currentNumberOfGoals = 10
//
//        gameDao.update(firstHomeTeam, firstAwayTeam)
//        gameDao.update(secondHomeTeam, secondAwayTeam)
//
//        when:
//        def gamesByTotalScore = gameDao.getSummaryByTotalScore()
//
//        then:
//        noExceptionThrown()
//    }

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
