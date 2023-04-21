package tictactoe;

import javax.swing.JOptionPane;

/**
 * Controller of the Tic Tac Toe game.

 * @author ZongyuWu
 *
 */
public class TicTacToeControllerImpl implements TicTacToeController {
  
  private TicTacToe model;
  private TicTacToeView view;
  
  /**
   * Constructor of the controller.

   * @param tttView view
   * @param tttModel model
   */
  public TicTacToeControllerImpl(TicTacToeView tttView, TicTacToe tttModel) {
    if (tttModel == null || tttView == null) {
      throw new IllegalArgumentException("Model or View is null.");
    }
    
    this.model = tttModel;
    this.view = tttView;
  }

  @Override
  public void playGame() {
    this.view.addClickListener(this);
    this.view.refresh();
  }

  @Override
  public void handleCellClick(int row, int col) {
    if (row < 0 || col < 0) {
      return;
    }
    if (row > 2 || col > 2) {
      return;
    }
    
    try {
      this.model.move(row, col);
    } catch (IllegalStateException e) {
      JOptionPane.showMessageDialog(null, "Game over, cannot move.");
    }
    
    this.view.refresh();
    
    if (this.model.isGameOver()) {
      if (this.model.getWinner() != null) {
        JOptionPane.showMessageDialog(null, 
            "Game over. The winner is " + this.model.getWinner() + ".");
      } else {
        JOptionPane.showMessageDialog(null, "Game over. Tie game.");
      }
      
    }
  }

}
