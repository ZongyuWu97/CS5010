package tictactoe;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * Represents a panel in the GUI.

 * @author ZongyuWu
 *
 */
public class TicTacToePanel extends JPanel {
  
  private static final long serialVersionUID = 1L;
  private Player[][] board;
  
  /**
   * Constructor of a panel.
   */
  public TicTacToePanel() {
    this.setBackground(Color.WHITE);
    this.setSize(500, 500);
    this.setVisible(true);
    this.board = new Player[3][3];
  }
  
  /**
   * set the board.

   * @param board board
   */
  public void setBoard(Player[][] board) {
    this.board = board;
    this.repaint();
  }
  
  @Override
  public void paintComponent(Graphics g) {
    g.fillRect(166, 0, 2, 500);
    g.fillRect(332, 0, 2, 500);
    g.fillRect(0, 166, 500, 2);
    g.fillRect(0, 332, 500, 2);

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        Player current = this.board[i][j];
        if (current == Player.O) {
          g.fillOval(30 + j * 166, 30 + i * 166, 106, 106);
        } else if (current == Player.X) {
          g.fillRect(30 + j * 166, 30 + i * 166, 106, 106);
        }
      }
    }
  }
}
