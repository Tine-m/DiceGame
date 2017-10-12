package dicegame;

import java.util.Random;

class Die {

    private int faceValue;
    private int noSides;
    private Random r = new Random();

    Die(int noSides) {
        if (noSides < 1) {
            throw new IllegalArgumentException();
        }
        this.noSides = noSides;
    }

    public void roll() {
        faceValue = r.nextInt(noSides) + 1;
    }

    public int getFaceValue() {
        return faceValue;
    }

}
