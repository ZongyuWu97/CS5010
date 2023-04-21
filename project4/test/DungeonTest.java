import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.PrintStream;
import java.util.Scanner;
import model.DungeonController;
import model.DungeonControllerImpl;
import model.DungeonModel;
import model.DungeonModelImpl;
import model.Location;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * Test for the dungeon model and controller.

 * @author ZongyuWu
 *
 */
public class DungeonTest {

  private DungeonModel model;
  private DungeonController controller;
  private Scanner scanner;
  private PrintStream ps;
  
  /**
   * Set up a model and a controller.
   */
  @Before
  public void setUp() {
    model = new DungeonModelImpl();
    scanner = new Scanner("wrapping 7 7 0");
    ps = System.out;
    controller = new DungeonControllerImpl(model, scanner, ps);
    controller.startGame(50, 1);
    model.setStartEnd(0, model.getEnd().getIndex());
  }

  @Test
  public void test() {
    model.setStartEnd(0, model.getEnd().getIndex());

    // Monster at end.
    assertTrue(model.getEnd().hasMonster());
    
    //Correct number of monster added.
    int countMonster = 0;
    for (Location cave : model.getCaves().values()) {
      if (cave.hasMonster()) {
        countMonster++;
      }
    }
    assertEquals(1, countMonster);
    
    // Smell one monster correct
    model.move(model.getStart());
    model.getLocation().addMonster();
    controller.move("south");
    controller.move("south");
    assertTrue(model.playerLocation().indexOf("Less") != -1);
    
    // Smell two monster correct
    model.move(model.getStart());
    controller.move("east");
    model.getLocation().addMonster();
    controller.move("east");
    assertTrue(model.playerLocation().indexOf("Strong") != -1);

    // Correct number of arrows added.
    int countArrow = 0;
    for (Location location : model.getLocations().values()) {
      if (location.getArrow()) {
        countArrow++;
      }
    }
    assertEquals(24, countArrow);
    
    
    // Player can pick up arrow.
    model.getLocation().setArrow();
    assertTrue(model.getLocation().getArrow());
    model.pick();
    assertFalse(model.getLocation().getArrow());

    // Can shoot arrow
    model.getLocation().addMonster();
    assertEquals(1, model.shoot("north", 0));
    
    // Arrow travel 
    model.move(model.getStart());
    model.shoot("south", 3);
    
    // Correct direction miss monster. Already a monster at start.
    controller.move("south");
    assertEquals(-1, model.shoot("north", 2));
    
    // Player killed.
    controller.move("north");
    assertTrue(model.getPlayer().isDead());
    
    // Chance of survive.
    int count = 0;
    model.move(model.getStart());
    model.getLocation().addMonster();
    controller.move("north");
    for (int i = 0; i < 100; i++) {
      model.shoot("south", 1);
      controller.move("south");
      if (!model.getPlayer().isDead()) {
        count++;
      }
      model.getLocation().addMonster();
      controller.move("north");
      model.getPlayer().recover();
      model.getPlayer().setArrow(3);
    }
    assertTrue(count > 0);
    
    // Kill a monster
    model.getPlayer().recover();
    model.move(model.getStart());
    model.getLocation().addMonster();
    controller.move("north");
    assertEquals(1, model.shoot("south", 1));
    assertEquals(0, model.shoot("south", 1));
    controller.move("south");
    assertFalse(model.getPlayer().isDead());
    
    // Controller can move
    model.move(model.getStart());
    controller.move("east");
    assertEquals(1, model.getLocation().getIndex());
    
    // Controller can pick up
    model.getLocation().setArrow();
    controller.pick();
    int countZero = 0; // count number of 0s in the description. 
    int startIndex = 0;
    while (model.playerLocation().indexOf("0", startIndex) != -1) {
      startIndex = model.playerLocation().indexOf("0", startIndex) + 1;
      countZero++;
    }
    assertEquals(4, countZero);
    assertTrue(model.playerLocation().indexOf("no") != -1);
    
    // Controller handle shoot.
    model.move(model.getStart());
    model.getLocation().addMonster();
    controller.move("north");
    controller.shoot("south", 1);
    assertEquals(1, model.getStart().getMonsterHealth());
    
    // Quit
    model = new DungeonModelImpl();
    scanner = new Scanner("wrapping 7 7 0 q");
    ps = System.out;
    controller = new DungeonControllerImpl(model, scanner, ps);
    controller.startGame(50, 1);
    
    // Null model
    model = null;
    scanner = new Scanner("wrapping 7 7 0");
    ps = System.out;
    controller = new DungeonControllerImpl(model, scanner, ps);
    controller.startGame(50, 1);    
  }
  
  @After
  public void after() {
    scanner.close();
  }

}
