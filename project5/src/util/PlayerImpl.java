package util;

import java.util.HashMap;

/**
 * This class implements the Player interface.

 * @author ZongyuWu
 *
 */
public class PlayerImpl implements Player {
  
  private Location location;
  private HashMap<Treasure, Integer> treasures;
  private int arrow;
  private boolean alive;

  /**
   * Constructor of a player.
   */
  public PlayerImpl() {
    this.treasures = new HashMap<Treasure, Integer>();
    for (Treasure t : Treasure.values()) {
      this.treasures.put(t, 0);
    }
    this.arrow = 3;
    this.alive = true;
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

  @Override
  public void pick() {
    Location l = this.getLocation();
    if (l == null) {
      throw new IllegalArgumentException("Null location.");
    }
    
    if (l.getArrow()) {
      this.arrow += 1;
    }
    for (Treasure t : l.getTreasures().keySet()) {
      this.addTreasure(t, l.getTreasures().get(t));
    }    
    
    l.resetTreasure();
    l.resetArrow();
  }

  @Override
  public void setArrow(int num) {
    this.arrow = num;
  }

  @Override
  public int getArrow() {
    return this.arrow;
  }

  @Override
  public boolean isDead() {
    return !alive;
  }

  @Override
  public void setDead() {
    this.alive = false;
  }
  
  @Override
  public void recover() {
    this.alive = true;
  }

}
