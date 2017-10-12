package dicegameapp;

import java.util.Random;

class Die {

    private int faceValue;
    private int noSides;
    private Random r = new Random();
    
    Die(int noSides) {
        this.noSides = noSides;
    }
    
    public void roll() {
        faceValue = r.nextInt(noSides) + 1;      
    }
    
    public int getFaceValue() {
        return faceValue;
    }
            
    
}
