package battle;

/**
 * Represents a gear for player.

 * @author ZongyuWu
 *
 */
public abstract class Gear {
  
  private final int strength;
  private final int constitution;
  private final int dexterity;
  private final int charisma;
  
  /**
   * Different gear affects different ability.

   * @param strength ability.
   * @param constitution ability.
   * @param dexterity ability.
   * @param charisma ability.
   */
  public Gear(int strength, int constitution, int dexterity, int charisma) {
    this.strength = strength;
    this.constitution = constitution;
    this.dexterity = dexterity;
    this.charisma = charisma; 
  }

  public int getStrengthChange() {
    return this.strength;
  }
  
  public int getConstitutionChange() {
    return this.constitution;
  }
  
  public int getDexterityChange() {
    return this.dexterity;
  }
  
  public int getCharismaChange() {
    return this.charisma;
  }
}
