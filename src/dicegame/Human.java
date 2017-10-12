package dicegame;

import java.util.ArrayList;

public class Human extends Player {
    
    public Human(String name) {
        super(name);
    }
    
    @Override
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
                System.out.println("Unfortunately there are no dice you can keep. You lost your points this turn.");
                return 0;
            }
        }

    }

   
   
}
