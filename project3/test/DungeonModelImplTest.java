import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import dungeon.DungeonModel;
import dungeon.DungeonModelImpl;
import dungeon.Location;
import org.junit.Before;
import org.junit.Test;


/**
 * Test the DungeonModelImpl class.

 * @author ZongyuWu
 *
 */
public class DungeonModelImplTest {
  
  DungeonModel dungeon;

  /**
   * Set up a dungeon for further use.
   */
  @Before
  public void setUp() {
    this.dungeon = new DungeonModelImpl();
    this.dungeon.create("wrapping", 7, 7, 50);
    this.dungeon.setStartEnd(0, 48);
  }

  @Test
  public void testRandom() {
    DungeonModel tmp = new DungeonModelImpl();
    tmp.create("wrapping", 7, 7, 50);
    tmp.setStartEnd(0, 7);
    assertEquals(tmp.getStart().getIndex(), 0);
  }
  
  @Test
  public void testNonWrapping() {
    DungeonModel tmp = new DungeonModelImpl();
    tmp.create("non-wrapping", 1, 10, 0);
    tmp.setStartEnd(0, 9);
    tmp.addTreasure(50, true);
    tmp.enter();
    tmp.pick();
    
    int countTreasureCave = 0;
    int locationVisited = 1;
    
    for (int i = 0; i < 9; i++) {
      locationVisited++;
      for (int num : tmp.getLocation().getTreasures().values()) {
        if (num > 0) {
          countTreasureCave++;
          break;
        }
      }
      tmp.move(tmp.getNextLocation(tmp.getLocation(), "east"));
      tmp.pick();
    }
    
    for (int num : tmp.getLocation().getTreasures().values()) {
      if (num > 0) {
        countTreasureCave++;
      }
    }
    
    // 50 percent of caves are added treasure
    assertEquals(1, countTreasureCave);
    
    // Player reached end
    assertEquals(tmp.getEnd(), tmp.getLocation());
    
    // Player visited all locations
    assertEquals(10, locationVisited);
    
    // Player pick up treasure
    int sum = 0;
    for (int num : tmp.getPlayer().getTreasures().values()) {
      sum += num;
    }
    assertTrue(sum > 0);
    
  }
  
  @Test
  public void testWrapping() {
    this.dungeon.enter();
    Location here = this.dungeon.getLocation();

    // Player start at start
    assertEquals(here.getIndex(), 0);
    
    // Player description
    assertEquals(this.dungeon.playerDescription(), "The player has collected:\n"
        + "0 Diamond\n"
        + "0 Ruby\n"
        + "0 Sapphire\n"
    );
    
    // Player location
    assertEquals(this.dungeon.playerLocation(), "This location is row 0, column 0.\n"
        + "The current location has\n"
        + "0 Diamond\n"
        + "0 Ruby\n"
        + "0 Sapphire\n"
        + "This location is connected to the north.\n"
        + "This location is connected to the west.\n"
        + "This location is connected to the south.\n"
        + "This location is connected to the east.\n"
    );
    
    // Player move in four direction
    
    // Verify start location
    assertEquals(here.getRow(), 0);
    assertEquals(here.getColumn(), 0);
    
    // Move north
    this.dungeon.move(this.dungeon.getNextLocation(here, "north"));
    assertEquals(this.dungeon.getLocation().getRow(), 6);
    assertEquals(this.dungeon.getLocation().getColumn(), 0);   
    
    // Move south
    this.dungeon.move(this.dungeon.getNextLocation(this.dungeon.getLocation(), "south"));
    assertEquals(this.dungeon.getLocation().getRow(), 0);
    assertEquals(this.dungeon.getLocation().getColumn(), 0);    

    // Move west
    this.dungeon.move(this.dungeon.getNextLocation(this.dungeon.getLocation(), "west"));
    assertEquals(this.dungeon.getLocation().getRow(), 0);
    assertEquals(this.dungeon.getLocation().getColumn(), 6);    

    // Move east
    this.dungeon.move(this.dungeon.getNextLocation(this.dungeon.getLocation(), "east"));
    assertEquals(this.dungeon.getLocation().getRow(), 0);
    assertEquals(this.dungeon.getLocation().getColumn(), 0);  
    

  }



}
