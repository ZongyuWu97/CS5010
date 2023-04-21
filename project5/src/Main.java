import controller.DungeonController;
import controller.DungeonControllerImpl;
import model.DungeonModel;
import model.DungeonModelImpl;
import view.DungeonGuiView;
import view.DungeonView;

public class Main {
  

  public static void main(String[] args) {
    DungeonModel model = new DungeonModelImpl();
    DungeonView view = new DungeonGuiView(model);
    
    DungeonController controller = new DungeonControllerImpl(model, view);
    


  }

}
