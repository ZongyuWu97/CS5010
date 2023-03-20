package battle;

/**
 * Weapon.

 * @author ZongyuWu
 *
 */
public enum Weapon {
  Katanas(4, 6, 0), 
  Broadswords(6, 10, 0), 
  TwoHandedSwords(8, 12, 14),
  Axes(6, 10, 0),
  Flails(8, 12, 14);
  
  private int low;
  private int high;
  private int requirement;
  
  private Weapon(int low, int high, int requirement) {
    this.low = low;
    this.high = high;
    this.requirement = requirement;
  }
  
  public int getLow() {
    return this.low;
  }
  
  public int getHigh() {
    return this.high;
  }
  
  public int getRequirement() {
    return this.requirement;
  }
}
