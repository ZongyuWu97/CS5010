package tictactoe;

/**
 * Run a TicTacToe game interactively.
 */
public class Main {
  /**
   * Run a TicTacToe game interactively.
   */
  public static void main(String[] args) {
    // Old News: console-based game:
    //new TicTacToeConsoleController(new InputStreamReader(System.in),
    //    System.out).playGame(new TicTacToeModel());

    // New Hotness: Graphical User Interface:
    // 1. Create an instance of the model.
    // 2. Create an instance of the view.
    // 3. Create an instance of the controller.
    // 4. Call playGame() on the controller.
    TicTacToe model = new TicTacToeModel();
    TicTacToeView view = new TicTacToeGuiView(model);
    
    view.refresh();
    model.move(0, 0);
    
    view.refresh();
    model.move(0, 1);
    
    view.refresh();
    model.move(0, 2);
    
    view.refresh();
    model.move(1, 0);
    
    view.refresh();
    model.move(1, 1);
    
    view.refresh();
    model.move(1, 2);
    
    view.refresh();
    model.move(2, 0);
    
    view.refresh();
    System.out.println(model.getTurn());
    System.out.println(model.toString());
    System.out.println(model.isGameOver());
  }
}
