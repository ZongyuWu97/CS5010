import static org.junit.Assert.assertEquals;

import battle.Weapon;
import org.junit.Test;

/**
 * Test the weapon enum.

 * @author ZongyuWu
 *
 */
public class WeaponTest {

  private Weapon axes = Weapon.Axes;

  @Test
  public void testWeaponRange() {
    assertEquals(6, axes.getLow());
    assertEquals(10, axes.getHigh());
  }

}
