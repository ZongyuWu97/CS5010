import static org.junit.Assert.assertEquals;

import org.junit.Test;
import tictactoe.Player;
import tictactoe.TicTacToeController;
import tictactoe.TicTacToeControllerImpl;
import tictactoe.TicTacToeGuiView;
import tictactoe.TicTacToeModel;

/**
 * Test the controller.
 */
public class TicTacToeControllerTest {

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullModel() {
    new TicTacToeControllerImpl(new TicTacToeGuiView(null), null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullView() {
    new TicTacToeControllerImpl(null, new TicTacToeModel());
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testHandelClickInvalid() {
    TicTacToeModel model = new TicTacToeModel();
    TicTacToeController controller = new TicTacToeControllerImpl(
        new TicTacToeGuiView(model), model);
    controller.handleCellClick(1, 0);
    controller.handleCellClick(1, 0);
  }
  
  @Test
  public void testHandelClickValid() {
    TicTacToeModel model = new TicTacToeModel();
    TicTacToeController controller = new TicTacToeControllerImpl(
        new TicTacToeGuiView(model), model);
    controller.handleCellClick(1, 0);
    assertEquals(Player.X, model.getMarkAt(1, 0));
  }
}
 