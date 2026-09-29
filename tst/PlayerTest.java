import Util.Player;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class PlayerTest {
    @Test
    public void testExpChecker() {
        Player iWantToCry = new Player("Carl");
        int newValue = iWantToCry.getExpRequiredForLeveling( 3);
        System.out.println(newValue);
        assertEquals(275, newValue);
    }
}
