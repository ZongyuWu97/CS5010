package tictactoe;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Represents a mouse event.

 * @author ZongyuWu
 *
 */
public class TicTacToeMouse extends MouseAdapter {
  
  private TicTacToeController listener;
  
  /**
   * Constructor of a mouse.

   * @param controller controller
   */
  public TicTacToeMouse(TicTacToeController controller) {
    this.listener = controller;
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    int row = e.getY() / 166;
    int col = e.getX() / 166;
    
    this.listener.handleCellClick(row, col);
  }
}
