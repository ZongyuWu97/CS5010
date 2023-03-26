package dungeon;

import java.util.HashMap;

/**
 * This class implements the Player interface.

 * @author ZongyuWu
 *
 */
public class PlayerImpl implements Player {
  
  private Location location;
  private HashMap<Treasure, Integer> treasures;

  /**
   * Constructor of a player.
   */
  public PlayerImpl() {
    this.treasures = new HashMap<Treasure, Integer>();
    for (Treasure t : Treasure.values()) {
      this.treasures.put(t, 0);
    }
  }
  
  @Override
  public void move(Location location) {
    this.location = location;

  }

  @Override
  public Location getLocation() {
    return this.location;
  }

  @Override
  public HashMap<Treasure, Integer> getTreasures() {
    return this.treasures;
  }

  @Override
  public void addTreasure(Treasure treasure, int num) {
    this.treasures.put(treasure, this.treasures.get(treasure) + num);
  }

}
