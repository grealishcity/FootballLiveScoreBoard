package com.grealishcity.scoreboard.validator

import spock.lang.Specification
import spock.lang.Subject

class TeamValidatorSpec extends Specification {

    @Subject
    def teamValidator = new TeamValidator();

    def "should return true when given teams names are correct"() {
        given:

        when:

        then:
    }
}
