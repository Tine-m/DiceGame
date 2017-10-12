package dicegameapp;

import java.util.ArrayList;

/**
 *
 * @author TM
 * 
 */
public class DiceGameApp {

    public static void main(String[] args) {
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Human("Joey"));
        players.add(new AI("Computer"));
        Game game = new Game(players, 8, 6, 100);
        game.play();
    }
    
}
