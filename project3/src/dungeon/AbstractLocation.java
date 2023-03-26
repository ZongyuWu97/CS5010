package dungeon;

import java.util.HashMap;
import java.util.HashSet;

/**
 * This abstract class implements the location interface.

 * @author ZongyuWu
 *
 */
public abstract class AbstractLocation implements Location {
  protected HashMap<Treasure, Integer> treasures;
  private boolean north = false;
  private boolean west = false;
  private boolean south = false;
  private boolean east = false;
  private int index;
  private final int row;
  private final int col;
  private HashMap<String, Integer> directions;
  
  /**
   * Constructor of a location.

   * @param row row of this location
   * @param col col of this location
   * @param x index of this location
   * @param neighbors neighbors of this location
   */
  public AbstractLocation(int row, int col, int x, HashSet<Integer> neighbors) {
    this.index = x;
    this.directions = new HashMap<>();
    for (int y : neighbors) {
      if ((x - col == y) | ((x < col) & (y == x + (row - 1) * col))) {
        this.north = true;
        directions.put("north", y);
      }
      if ((x - 1 == y) | ((x % col == 0) & (x + col - 1 == y))) {
        this.west = true;
        directions.put("west", y);
      }
      if ((x + col == y) | ((x >= (row - 1) * col) & (x - (row - 1) * col == y))) {
        this.south = true;
        directions.put("south", y);
      }
      if ((x == y - 1) | ((x % col == col - 1) & (x - (col - 1) == y))) {
        this.east = true;
        directions.put("east", y);
      }
    }
    this.treasures = new HashMap<>();
    for (Treasure t : Treasure.values()) {
      this.treasures.put(t, 0);
    }
    this.row = x / col;
    this.col = x % col;
  }
  
  @Override
  public boolean getNorth() {
    return this.north;
  }
  
  @Override
  public boolean getWest() {
    return this.west;
  }
  
  @Override
  public boolean getSouth() {
    return this.south;
  }
  
  @Override
  public boolean getEast() {
    return this.east;
  }
  
  @Override
  public int getIndex() {
    return this.index;
  }
  
  @Override
  public HashMap<Treasure, Integer> getTreasures() {
    return this.treasures;
  }
  
  @Override
  public int getRow() {
    return this.row;
  }

  @Override
  public int getColumn() {
    return this.col;
  }
  
  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Location)) {
      return false;
    } else if (this == o) {
      return true;
    }
    return (this.getRow() == ((Location) o).getRow()) 
        & (this.getColumn() == ((Location) o).getColumn());
  }
  
  @Override
  public int getNextLocation(String direction) {
    return this.directions.get(direction);
  }
  
  @Override
  public int hashCode() {
    return this.index;
  }


}
