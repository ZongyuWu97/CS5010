package model;

import java.util.HashMap;
import util.Location;
import util.Player;

/**
 * This interface represents a dungeon. It have locations that 
 * can be further defined as caves and tunnels. Tunnels are locations 
 * with 2 entrance, and caves are those with 1, 3, and 4 entrance.
 * Each cave can more than one treasure.

 * @author ZongyuWu
 *
 */
public interface DungeonModel {
  
  /**
   * Creates a dungeon with specified number of rows and columns. 
   * The dungeon can be wrapping or non wrapping.
   * Can specify the interconnectivity.
   * Random start and end.

   * @param wrap A String of wrapping or non-wrapping.
   * @param row Number of rows.
   * @param col Number of columns.
   * @param interconnectivity interconnectivity.
   * @return A string that shows how many connects each location has.
   * @throws IllegalArgumentException For invalid input.
   */
  public String create(String wrap, int row, int col, int interconnectivity) 
      throws IllegalArgumentException;

  /**
   * Set the start and end automatically. 
   */
  public void setStartEnd();
  
  /**
   * Set the start and end to given index. 

   * @param start specify the start index
   * @param end specify the end index
   */
  public void setStartEnd(int start, int end);
  
  /**
   * Add 1, 2, or 3 treasures to specified percent of caves.
   * Add arrows to specified percent of locations.

   * @param percentage percent.
   * @throws IllegalArgumentException For invalid percentage.
   */
  public void addTreasure(int percentage) throws IllegalArgumentException;
  
  /**
   * Add 1, 2, or 3 treasures to specified percent of caves 
   * starting from the first cave.

   * @param percentage percent.
   * @param noRandom no random in this case
   * @throws IllegalArgumentException For invalid percentage.
   */
  public void addTreasure(int percentage, boolean noRandom) throws IllegalArgumentException;
  
  /**
   * Player enter the dungeon at start.

   */
  public void enter();
  
  /**
   * Description of treasures the player has collected.

   * @return A string of treasures.
   * @throws IllegalArgumentException If there's no player.
   */
  public String playerDescription() throws IllegalArgumentException;
  
  /**
   * Description of the player's location that includes a description 
   * of treasure in the room and the possible moves (north, east, south, west) 
   * that the player can make from the current location.

   * @return string of description
   * @throws IllegalArgumentException If there's no player.
   */
  public String playerLocation() throws IllegalArgumentException;
  
  /**
   * Player move to the given direction.

   * @param location the next location
   * @throws IllegalArgumentException If can't move to the given 
   *     direction or if there's no player..
   */
  public void move(Location location) throws IllegalArgumentException;
  
  /**
   * Pick up the treasure at the current cave.

   * @throws IllegalArgumentException If there's no player.
   */
  public void pick() throws IllegalArgumentException;
  
  /**
   * Number of rows of this dungeon.

   * @return row
   */
  public int getRow();
  
  /**
   * Number of columns of this dungeon.

   * @return columns
   */
  public int getColumn();
  
  /**
   * Number of interconnectivity of this dungeon.

   * @return interconnectivity
   */
  public int getInterconnectivity();
  
  /**
   * Get the start location.

   * @return start location
   */
  public Location getStart();

  /**
   * Get the end location.

   * @return end location
   */
  public Location getEnd();
  
  /**
   * Get the next location.

   * @param location current location
   * @param direction direction to move
   * @return a location object
   */
  public Location getNextLocation(Location location, String direction);
  
  /**
   * Get the current location of the player.

   * @return Location object
   */
  public Location getLocation();
  
  /**
   * Get the player in the dungeon.

   * @return Player
   */
  public Player getPlayer();
  
  /**
   * Add monster to the dungeon.

   * @param num number of monsters in the dungeon
   */
  public void addMonster(int num);
  
  /**
   * 2 means more smell, 1 means less smell, 0 means no smell.

   * @return smell level of the monster.
   */
  public int detectMonster();
  
  /**
   * Shoot an arrow at specified direction and distance.

   * @param direction direction
   * @param distance distance
   * @return 0 monster dead, 1 monster injured, -1 no monster here
   */
  public int shoot(String direction, int distance);
  
  /**
   * Return the locations.

   * @return locations
   */
  public HashMap<Integer, Location> getLocations();
  
  /**
   * Return the caves.

   * @return caves
   */
  public HashMap<Integer, Location> getCaves();

  /**
   * Get the current dungeon board.

   * @return The current board
   */
  public Location[][] getBoard();
  
  /**
   * Reset the board.

   * @param board board
   */
  public void setBoard(Location[][] board);
}
