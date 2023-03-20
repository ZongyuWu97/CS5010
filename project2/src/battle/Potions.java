package battle;

/**
 * Gear of type potion.

 * @author ZongyuWu
 *
 */
public class Potions extends Gear {

  /**
   * potion affects only one ability.

   * @param strength ability.
   * @param constitution ability.
   * @param dexterity ability.
   * @param charisma ability.
   */
  public Potions(int strength, int constitution, int dexterity, int charisma) {
    super(strength, constitution, dexterity, charisma);
    int cnt = 0;
    if (strength != 0) {
      cnt++;
    }
    if (constitution != 0) {
      cnt++;
    }
    if (dexterity != 0) {
      cnt++;
    }
    if (charisma != 0) {
      cnt++;
    }
    
    if (cnt > 1) {
      throw new IllegalArgumentException("Potions affect only one ability.");
    }
    if (cnt == 0) {
      throw new IllegalArgumentException("Potions not affect any ability");
    }
  }

}
