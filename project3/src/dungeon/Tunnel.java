package dungeon;

import java.util.HashSet;

/**
 * Nothing different from the superclass.

 * @author ZongyuWu
 *
 */
public class Tunnel extends AbstractLocation {

  public Tunnel(int row, int col, int x, HashSet<Integer> neighbors) {
    super(row, col, x, neighbors);
  }

  @Override
  public void addTreasure() {    
  }

}
