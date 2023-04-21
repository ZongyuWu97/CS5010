package model;

import java.util.HashSet;
import java.util.Random;

/**
 * This class extends an abstract location. It can add treasure.

 * @author ZongyuWu
 *
 */
public class Cave extends AbstractLocation {
  
  public Cave(int row, int col, int x, HashSet<Integer> neighbors) {
    super(row, col, x, neighbors);
  }

  @Override
  public void addTreasure() {
    Random r = new Random();
    Treasure tmp;
    for (int i = 0; i < 1 + r.nextInt(3); i++) {
      tmp = Treasure.values()[r.nextInt(3)];
      this.treasures.put(tmp, this.treasures.get(tmp) + 1);
    }
  }

  @Override
  public void addMonster() {
    this.monster = true;
    this.health = 2;
  }
  
  

}
