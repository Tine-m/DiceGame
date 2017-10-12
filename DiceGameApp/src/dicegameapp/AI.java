package dicegameapp;

import java.util.ArrayList;
import java.util.Scanner;

public class AI implements Player {

    private final String name;  
    private int score;
    private ArrayList<Die> dice;// = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    public AI(String name) {
        this.name = name;
        score = 0;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public int takeTurn() {     
        boolean[] stored = new boolean[dice.size()];

        ArrayList<Die> keep = new ArrayList<>();

        int result = 0;
        for (int i = 0; i < dice.size(); i++) {
            dice.get(i).roll();
            result = dice.get(i).getFaceValue();
            score += result;         
        }

        while (true) {
            showScore(score);
            showStored(keep);
            showRoll(dice);
            int choice = whichToKeep(dice);
            showChoice(choice);
            int removed = 0;
            ArrayList<Die> tmp = new ArrayList<>();
            for (int i = 0; i < dice.size(); ++i) {
                if (dice.get(i).getFaceValue() == choice) {
                    ++removed;
                    score += choice;
                    stored[choice - 1] = true;
                    keep.add(dice.get(i));
                } else {
                    tmp.add(dice.get(i));
                }
            }
            dice = tmp;
            if (removed == 0) {
                System.out.println("There where no " + choice + "'s to keep, you lost your points this turn.");
                return 0;
            }
            if (dice.isEmpty()) {
                System.out.println("All dice used, double score!");
                return 2 * score;
            }
            if (!rollAgain()) {
                System.out.println("You have chosen to stop here.");
                return score;
            }
            //Roll remaining dice
            for (Die die : dice) {
                die.roll();
            }
            //Check for possible solution.
            boolean ok = false;
            for (Die die : dice) {
                if (!stored[die.getFaceValue() - 1]) {
                    ok = true;
                    break;
                }
            }
            if (!ok) {
                showScore(score);
                showStored(keep);
                showRoll(dice);
                System.out.println("Unfortunatly there are no dice you can keep. You lost your points this turn.");
                return 0;
            }
        }

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

    private int whichToKeep(ArrayList<Die> stored) {
        System.out.print("What number of pips do you want to keep? ");
        String answer = scanner.nextLine();
        return Integer.parseInt(answer);
    }

    private void showScore(int score) {
        System.out.println("You have scored " + score + " points so far in this turn.");
    }

    private void showChoice(int choice) {
        System.out.println("Keep the " + choice + "'s.");
    }

    private void showRoll(ArrayList<Die> rolled) {
        System.out.print("\nRolled:");
        for (Die d : rolled) {
            System.out.print(" " + d.getFaceValue());
        }
        System.out.println(".");
    }

    private void showStored(ArrayList<Die> stored) {
        int[] types = distribution(stored);
        System.out.println("Stored dice: ");
        for (int i = 0; i < types.length; ++i) {
            System.out.println((i + 1) + "'s: " + types[i]);
        }
    }

    private int[] distribution(ArrayList<Die> dice) {
        int[] res = new int[8];
        for (Die d : dice) {
            res[d.getFaceValue() - 1]++;
        }
        return res;
    }
   
}
