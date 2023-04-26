import controller.DungeonController;
import controller.DungeonControllerImpl;
import model.DungeonModel;
import model.DungeonModelImpl;
import view.DungeonGuiView;
import view.DungeonView;

/**
 * Main class for the dungeon app.

 * @author ZongyuWu
 *
 */
public class Main {
  

  /**
   * main function for the dungeon game.

   * @param args No input.
   */
  public static void main(String[] args) {
    DungeonModel model = new DungeonModelImpl();
    DungeonView view = new DungeonGuiView(model);
    
    DungeonController controller = new DungeonControllerImpl(model, view);
    
    controller.playGame();
    


  }

}
