package dicegameapp;

import java.util.ArrayList;

/**
 *
 * @author TM
 */
public interface Player {

    public int takeTurn();

    public void setDice(int noDice, int noSides);

    public boolean rollAgain();

    public String getName();

    public int getScore();

}
