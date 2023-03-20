package battle;

import java.util.List;

/**
 * Reperesents a player.

 * @author ZongyuWu
 *
 */
public interface Player {
  
  /**
   * Assign ability to each category.

   * @param strength strength
   * @param constitution constitution
   * @param dexterity dexterity
   * @param charisma charisma
   */
  public void assignAbility(int strength, int constitution, 
      int dexterity, int charisma);
  
  /**
   * Get the player's name.

   * @return name of the player.
   */
  public String getName();
  
  /**
   * Get the total ability to be assigned.

   * @return total ability.
   */
  public int getTotalAbility();
  
  /**
   * Ability without gears.

   * @return abilities.
   */
  public String getBasicAbility();
  
  /**
   * Get player's strength.

   * @return strength
   */
  public int getStrength();
  
  /**
   * Get player's Constitution.

   * @return Constitution
   */
  public int getConstitution();
  
  /**
   * Get player's Dexterity.

   * @return Dexterity
   */
  public int getDexterity();
  
  /**
   * Get player's Charisma.

   * @return Charisma
   */
  public int getCharisma();
  
  /**
   * Add headgear.

   * @param headgear headgear
   */
  public void addHeadgear(Headgear headgear);
  
  /**
   * Add potions.

   * @param potions potions
   */
  public void addPotions(Potions potions);
  
  /**
   * Add belt.

   * @param belt belt
   */
  public void addBelt(Belts belt);
  
  /**
   * Add footwear.

   * @param footwear footwear
   */
  public void addFootwear(Footwear footwear);
  
  /**
   * Add weapon.

   * @param weapon weapon.
   */
  public void addWeapon(Weapon weapon);
  
  /**
   * Get Headgear.

   * @return player's headgear.
   */
  public Headgear getHeadgear();
  
  /**
   * Get potions.

   * @return player's potion.
   */
  public List<Potions> getPotions();
  
  /**
   * Get belt.

   * @return player's belts.
   */
  public List<Belts> getBelts();
  
  /**
   * Get footwear.

   * @return player's footwear.
   */
  public Footwear getFootwear();

  /**
   * Get weapon.

   * @return player's weapon.
   */
  public Weapon getWeapon();
  
  /**
   * Calculate remaining health of this player. A player's health is 
   * calculated to be the sum of their 4 abilities. Players start a 
   * battle with full health.

   * @return Player's remaining health.
   */
  public int remainingHealth();
  
  /**
   * The actual damage is the potential striking damage minus the 
   * constitution of their opponent. If the actual damage is greater
   *  than 0, it is subtracted from the player's health.

   *  @param damage the damage receivd.
   */
  public void receiveDamage(int damage);
  
  /**
   * Striking power is the sum of the strength of the player, any of 
   * the gear that adds (or subtracts) from strength, and a random 
   * number between 1 and 10 (inclusive).

   * @return Player's striking power. 
   */
  public int strikingPower();
  
  /**
   * Avoidance ability is the sum of the dexterity of the player, 
   * any of the gear that adds (or subtracts) from dexterity, and
   *  a random number between 1 and 6 (inclusive).

   * @return Player's avoidance ability.
   */
  public int avoidanceAbility();
  
  /**
   * The potential striking damage is calculated by adding the
   * strength of the attacking player to a random value in the
   * range of the damage that their weapon can inflict (if 
   * they have a weapon).
   * The actual damage is the potential striking damage minus the 
   * constitution of their opponent.

   * @return Player's potential striking damage.
   */
  public int potentialStrikingDamage();
  
  /**
   * Players have the ability to wear 10 "units" of belts where 
   * small belts count as 1 unit, medium as 2 units, and large 
   * as 4 units.

   * @return The remaining unit.
   */
  public int remainingBeltUnit();
  
  /**
   * Get the ability values after taking equipments into account.

   * @return 4 abilities.
   */
  public int[] getFinalAbility();
  
  /**
   * Reset this player's remaining health.
   */
  public void resetHealth();

}
