import static org.junit.Assert.assertEquals;

import controller.DungeonController;
import controller.DungeonControllerImpl;
import model.DungeonModel;
import model.DungeonModelImpl;
import org.junit.Before;
import org.junit.Test;
import view.DungeonGuiView;

/**
 * Test for the dungeon model and controller.

 * @author ZongyuWu
 *
 */
public class DungeonTest {

  private DungeonModel model;
  private DungeonController controller;
  private DungeonGuiView view;
  
  /**
   * Set up a model and a controller.
   */
  @Before
  public void setUp() {
    model = new DungeonModelImpl();
    view = new DungeonGuiView(model);
    controller = new DungeonControllerImpl(model, view);
    controller.startGame("wrapping", 7, 7, 0, 10, 1);
    model.setStartEnd(0, model.getEnd().getIndex());
  }

  @Test
  public void test() {
    model.setStartEnd(0, model.getEnd().getIndex());


    // Controller can move
    model.move(model.getStart());
    controller.move("east");
    assertEquals(1, model.getLocation().getIndex());
    
    
    // Controller handle shoot.
    model.move(model.getStart());
    model.getLocation().addMonster();
    controller.move("north");
    controller.shoot("south", 1);
    assertEquals(1, (int) model.getStart().getMonsterHealth().get(0));
    
  }
  

}
