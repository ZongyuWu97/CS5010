package dungeon;

import java.util.HashMap;

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

}
