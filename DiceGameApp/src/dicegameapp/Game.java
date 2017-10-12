package dicegameapp;

import java.util.ArrayList;

class Game {

    private ArrayList<Player> players;
    private final int gameEndPoints;

    public Game(ArrayList<Player> players, int numberOfDice, int numberOfSides, int gameEndPoints) {
        this.players = players;
        this.gameEndPoints = gameEndPoints;
        initPlayers(numberOfDice, numberOfSides);

    }

    public void play() {
        int turnScore = 0;

        boolean gameEnded = false;
        while (!gameEnded) {

            for (Player player : players) {
                System.out.println(player.getName() + " it is your turn!");
                System.out.print("Current score:");
                printScore();
                turnScore = player.takeTurn();

                if (turnScore > gameEndPoints) {
                    gameEnded = true;
                }
            }
        }
    }

    private void initPlayers(int noDice, int noSides) {

        for (Player player : players) {
            player.setDice(noDice, noSides);
        }
    }

    private void printScore() {
        for (int i = 0; i < players.size(); ++i) {
            System.out.println(players.get(i).getName() + ": " + players.get(i).getScore());
        }
    }

}
