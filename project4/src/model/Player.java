package model;

import java.util.HashMap;

/**
 * This interface represents a player in a dungeon.

 * @author ZongyuWu
 *
 */
public interface Player {
  
  /**
   * Player move to this location.

   * @param location location
   */
  public void move(Location location);
  
  /**
   * Get the current location of this player.

   * @return location.
   */
  public Location getLocation();
  
  /**
   * Get a list of the treasures this player has.

   * @return List of treasures.
   */
  public HashMap<Treasure, Integer> getTreasures();
  
  /**
   * Player pick up treasure.

   * @param treasure Pick up this treasure.
   * @param num amount to be added.
   */
  public void addTreasure(Treasure treasure, int num);
  
  /**
   * Pick up treasures and arrow at this location.
   */
  public void pick();
  
  /**
   * Set the number of arrows.

   * @param num number of arrows
   */
  public void setArrow(int num);
  
  /**
   * Get the number of arrows.

   * @return number of arrows
   */
  public int getArrow();
  
  /**
   * If the player is dead.

   * @return dead or not
   */
  public boolean isDead();
  
  /**
   * Set the player to dead.
   */
  public void setDead();

  /**
   * Retrieve the player.
   */
  public void recover();

}
