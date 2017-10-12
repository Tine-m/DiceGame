package dicegame;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tm
 */
public class DieTest {

    @Test
    public void rollDie6Sides() {
        Die d = new Die(6);

        for (int i = 0; i < 1000000; i++) {
            d.roll();
            int value = d.getFaceValue();
            assertTrue(value >= 1 && value <= 6);
        }
    }

    @Test
    public void rollDieMaxSides() {
        Die d = new Die(Integer.MAX_VALUE);

        for (int i = 0; i < 10000; i++) {
            d.roll();
            int value = d.getFaceValue();
            assertTrue(value >= 1 && value <= Integer.MAX_VALUE);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void createDieZeroSides() {
        Die d = new Die(0);

    }
}
