package dicegame;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class Player {

    protected final String name;  
    protected int score;
    protected ArrayList<Die> dice = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    
     public Player(String name) {
        this.name = name;
        score = 0;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public boolean rollAgain() {
        System.out.print("You have " + dice.size() + " dice left. Would you like to roll again? ");
        String s = scanner.nextLine();
        return s.equalsIgnoreCase("y") || s.equalsIgnoreCase("yes");
    }

    public int whichToKeep() {
        System.out.print("What number of pips do you want to keep? ");
        String answer = scanner.nextLine();
        return Integer.parseInt(answer);
    }

    public void setDice(int noDice, int noSides) {
        dice = new ArrayList<>();
        for (int i = 0; i < noDice; i++) {
            dice.add(new Die(noSides));
        }
    }

    protected int whichToKeep(ArrayList<Die> stored) {
        System.out.print("What number of pips do you want to keep? ");
        String answer = scanner.nextLine();
        return Integer.parseInt(answer);
    }

    protected void showScore(int score) {
        System.out.println("You have scored " + score + " points so far in this turn.");
    }

    protected void showChoice(int choice) {
        System.out.println("Keep the " + choice + "'s.");
    }

    protected void showRoll(ArrayList<Die> rolled) {
        System.out.print("\nRolled:");
        for (Die d : rolled) {
            System.out.print(" " + d.getFaceValue());
        }
        System.out.println(".");
    }

    protected void showStored(ArrayList<Die> stored) {
        int[] types = distribution(stored);
        System.out.println("Stored dice: ");
        for (int i = 0; i < types.length; ++i) {
            System.out.println((i + 1) + "'s: " + types[i]);
        }
    }

    protected int[] distribution(ArrayList<Die> dice) {
        int[] res = new int[8];
        for (Die d : dice) {
            res[d.getFaceValue() - 1]++;
        }
        return res;
    }

    public abstract int takeTurn();
}
