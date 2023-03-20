package battle;

/**
 * Gear of type footwear.

 * @author ZongyuWu
 *
 */
public class Footwear extends Gear {

  /**
   * footwear only affect dexterity.

   * @param strength 0.
   * @param constitution 0.
   * @param dexterity change this.
   * @param charisma 0.
   */
  public Footwear(int strength, int constitution, int dexterity, int charisma) {
    super(strength, constitution, dexterity, charisma);
    if (strength != 0 || constitution != 0 || charisma != 0) {
      throw new IllegalArgumentException("Footwear only affects dexterity.");
    }
    if (dexterity == 0) {
      throw new IllegalArgumentException("Footwear affects dexterity.");
    }  
  }

}
