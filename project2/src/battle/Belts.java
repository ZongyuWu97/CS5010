package battle;

/**
 * Gear of type belt.

 * @author ZongyuWu
 *
 */
public class Belts extends Gear {
  
  private Size size;
  
  /**
   * A belt can change up to two abilities.

   * @param strength ability.
   * @param constitution ability.
   * @param dexterity ability.
   * @param charisma ability.
   * @param size size of this belt.
   */
  public Belts(int strength, int constitution, int dexterity, int charisma, Size size) {
    super(strength, constitution, dexterity, charisma);
    this.size = size;
    
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
    if (cnt > 2) {
      throw new IllegalArgumentException("Belts affect up to two abilities.");
    }
    if (cnt == 0) {
      throw new IllegalArgumentException("Belts not affect any ability");
    }
  }
  
  /**
   * Compute how many unit this belt needs.

   * @return unit.
   */
  public int getUnit() {
    if (this.size == Size.Small) {
      return 1;
    } else if (this.size == Size.Medium) {
      return 2;
    } else {
      return 4;
    }
  }

}
