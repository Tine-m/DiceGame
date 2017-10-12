package dicegame;

import java.util.ArrayList;

public class AI extends Player {

    public AI(String name) {
        super(name);
    }
    
    @Override
    public int takeTurn() {     
       return score * 20;

    }

  
}
