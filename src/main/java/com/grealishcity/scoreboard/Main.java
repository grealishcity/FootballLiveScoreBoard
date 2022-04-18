package com.grealishcity.scoreboard;

import com.grealishcity.scoreboard.facade.BoardFacade;

public class Main {

    public static void main(String[] args) {
        BoardFacade facade = new BoardFacade();
        facade.start();
    }
}
