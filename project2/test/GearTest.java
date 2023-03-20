import static org.junit.Assert.assertEquals;

import battle.Belts;
import battle.Gear;
import battle.Size;
import org.junit.Before;
import org.junit.Test;

/**
 * Test the gear class.

 * @author ZongyuWu
 *
 */
public class GearTest {
  
  private Gear belt;

  @Before
  public void setUp() {
    this.belt = new Belts(5, 4, 0, 0, Size.Small);
  }

  @Test
  public void testCreator() {
    assertEquals(5, belt.getStrengthChange());
    assertEquals(4, belt.getConstitutionChange());
    assertEquals(0, belt.getDexterityChange());
    assertEquals(0, belt.getCharismaChange());
    assertEquals(1, ((Belts) belt).getUnit());
  }

}
