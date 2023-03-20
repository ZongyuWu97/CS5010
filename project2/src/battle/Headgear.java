package battle;

/**
 * Gear of type headgear.

 * @author ZongyuWu
 *
 */
public class Headgear extends Gear {

  /**
   * Headgear only affects constituion.

   * @param strength 0.
   * @param constitution change this.
   * @param dexterity 0.
   * @param charisma 0.
   */
  public Headgear(int strength, int constitution, int dexterity, int charisma) {
    super(strength, constitution, dexterity, charisma);
    if (strength != 0 || dexterity != 0 || charisma != 0) {
      throw new IllegalArgumentException("Headgear only affects constitution.");
    }
    if (constitution == 0) {
      throw new IllegalArgumentException("Headgear affects constitution.");
    }
  }

}
