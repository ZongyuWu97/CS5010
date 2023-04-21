package util;

import java.util.HashMap;
import java.util.List;

/**
 * This interface represents one location in a dungeon.

 * @author ZongyuWu
 *
 */
public interface Location {

  /**
   * Determines if this location is connected to the north.

   * @return true or false
   */
  public boolean getNorth();
  
  /**
   * Determines if this location is connected to the west.

   * @return true or false
   */
  public boolean getWest();

  /**
   * Determines if this location is connected to the south.

   * @return true or false
   */
  public boolean getSouth();

  /**
   * Determines if this location is connected to the east.

   * @return true or false
   */
  public boolean getEast();
  
  /**
   * Get the index of this location.

   * @return An integer index.
   */
  public int getIndex();
  
  /**
   * Add at most 3 treasures to this cave.
   */
  public void addTreasure();
  
  /**
   * Get the treasures at the current locations.

   * @return Treasure and their amounts.
   */
  public HashMap<Treasure, Integer> getTreasures();
  
  /**
   * Reset treasure to 0.
   */
  public void resetTreasure();
  
  /**
   * Get the row index of this location.

   * @return row
   */
  public int getRow();

  /**
   * Get the column index of this location.

   * @return column
   */
  public int getColumn();
  
  /**
   * Return the index of the next location.

   * @param direction direction
   * @return an index
   */
  public int getNextLocation(String direction);
  
  /**
   * Add arrow to this location.
   */
  public void setArrow();
  
  /**
   * Check if there's an arrow here.

   * @return If there's an arrow.
   */
  public boolean getArrow();
  
  /**
   * Reset arrow to 0.
   */
  public void resetArrow();
  
  /**
   * Add a monster to current location.
   */
  public void addMonster();
  
  /**
   * Get the remain health of monster.

   * @return 2 means full, 1 injured, 0 dead.
   */
  public List<Integer> getMonsterHealth();

  /**
   * Set the remain health of monster.

   * @param health remain health
   */
  public void setMonsterHealth(int health);
  
  /**
  * Return if there is a monster.

  * @return yes or no
  */
  public boolean hasMonster();
  
  /**
   * Get the monsters at this location.

   * @return
   */
  public List<Monster> getMonsters();
  
  /**
   * Set this location as visited.
   */
  public void setVisited();
  
  /**
   * Check if this location is visited.

   * @return visited or not
   */
  public boolean isVisited();
}
