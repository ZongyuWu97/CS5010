package battle;

/**
 * Represents a battle.

 * @author ZongyuWu
 *
 */
public interface Battle {
  
  /**
   * Add a player to the battle.

   * @param name Name of the player.
   * @return the added player.
   */
  public Player addPlayer(String name);
  
  /**
   * Assign abilities.

   * @param player assign ability of the player.
   * @param strength strength
   * @param constitution constitution
   * @param dexterity dexterity
   * @param charisma charisma
   */
  public void assign(Player player, int strength, int constitution, 
      int dexterity, int charisma);
  
  /**
   * Get players in this battle.

   * @return list of players in this battle.
   */
  public Player[] getPlayer();
  
  /**
   * Generate a bag of equipment that contains a minimum of 5 items of headgear, 
   * 5 items of footwear, 15 belts, and 15 potions. 25% of the items that are 
   * in the bag will diminish the player's ability rather than enhance it.

   * @return List of gears in the bag.
   */
  public Gear[] generateGearBag();
  
  /**
   * When players equip themselves from the bag, they are randomly assigned 20 
   * items from the bag. Any item that is randomly assigned must be used unless 
   * it cannot be combined with what the player is already using. 

   * @param player The player to be equipped.
   * @param gears The gear bag to be equipped.
   */
  public void equipPlayer(Player player, Gear[] gears);
  
  /**
   * players to request a weapon from the armory. Requests for a weapon are 
   * satisfied by randomly selecting one of the many weapons that are 
   * available (at least 1 of each type of weapon).

   * @param player The player that make request.
   */
  public void requestWeapon(Player player);
  
  /**
   * provide a complete description of players that will enter the arena 
   * including the player's temporary ability values (based on the affects
   * of the potions that they may have consumed) along with any and all 
   * the gear they are wearing, and what weapon they are using. Gear should
   * be returned in order of top to bottom, then alphabetically: thus any 
   * headgear should come before potions which come before any belts which 
   * should come before any footwear.

   * @param player The player to generate the description.
   * @return The description.
   */
  public String playerDescription(Player player);
  
  /**
   * Restart the battle.
   */
  public void restart();

}
