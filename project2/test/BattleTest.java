import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import battle.BattlePlayer;
import battle.Belts;
import battle.Footwear;
import battle.Gear;
import battle.Headgear;
import battle.Player;
import battle.Size;
import java.util.HashSet;
import org.junit.Before;
import org.junit.Test;


/**
 * Test execution of the model.

 * @author ZongyuWu
 *
 */
public class BattleTest {
  
  private Player player;
  private Gear headgear;
  private int total;
  
  /**
   * Setup a player and a headgear.
   */
  @Before
  public void setUp() {
    this.player = new BattlePlayer("player");
    this.total = player.getTotalAbility();
    player.assignAbility(total - 3, 1, 1, 1);
    this.headgear = new Headgear(0, 5, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidAbility() {
    player.assignAbility(player.getTotalAbility() - 4, 1, 1, 0);;
  }
  
  @Test
  public void testChangeInAbility() {
    player.addHeadgear((Headgear) this.headgear);
    assertEquals(1, player.getConstitution());
  }
  
  @Test
  public void testHealth() {
    assertEquals(total, player.remainingHealth());
  }
  
  @Test
  public void testStrikingPower() {
    assertEquals(total - 3, ((BattlePlayer) player).basesp());
  }
  
  @Test
  public void testAvoidance() {
    assertEquals(1, ((BattlePlayer) player).baseaa());
  }
  
  @Test
  public void testPotentialStrikingDamage() {
    assertEquals(total - 3, player.potentialStrikingDamage());
  }
  
  @Test
  public void testNegativeDamege() {
    player.receiveDamage(-1);
    assertEquals(total, player.remainingHealth());
  }
  
  @Test
  public void testUpdatingHealth() {
    player.receiveDamage(1);
    assertEquals(total - 1, player.remainingHealth());
  }
  
  @Test
  public void testBareHand() {
    assertTrue(player.getPotions().isEmpty());
    assertEquals(null, player.getWeapon());
  }
  
  @Test
  public void testOneHeadgear() {
    player.addHeadgear((Headgear) headgear);
    assertEquals(headgear, player.getHeadgear());
  }
  
  @Test
  public void testOneFootwear() {
    Footwear footwear1 = new Footwear(0, 0, 5, 0);
    Footwear footwear2 = new Footwear(0, 0, 10, 0);
    player.addFootwear(footwear1);
    player.addFootwear(footwear2);
    assertEquals(footwear2, player.getFootwear());
  }
  
  @Test
  public void testBeltUnit1() {
    Belts belt1 = new Belts(0, 0, 1, 0, Size.Large);
    Belts belt2 = new Belts(0, 0, 1, 0, Size.Medium);
    Belts belt3 = new Belts(0, 0, 1, 0, Size.Large);
    player.addBelt(belt1);
    player.addBelt(belt2);
    player.addBelt(belt3);
    HashSet<Belts> belts = new HashSet<>(player.getBelts());
    assertTrue(belts.contains(belt1));
    assertTrue(belts.contains(belt2));
    assertTrue(belts.contains(belt3));
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testBeltUnit2() {
    Belts belt1 = new Belts(0, 0, 1, 0, Size.Large);
    Belts belt2 = new Belts(0, 0, 1, 0, Size.Medium);
    Belts belt3 = new Belts(0, 0, 1, 0, Size.Large);
    Belts belt4 = new Belts(0, 0, 1, 0, Size.Large);
    player.addBelt(belt1);
    player.addBelt(belt2);
    player.addBelt(belt3);
    player.addBelt(belt4);
  }
  
}
