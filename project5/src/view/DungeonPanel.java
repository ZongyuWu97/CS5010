package view;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import util.Location;

/**
 * Extends the JPanel.

 * @author ZongyuWu
 *
 */
public class DungeonPanel extends JPanel {

  private static final long serialVersionUID = 1L;

  private Location[][] board;
  
  /**
   * Constructor of a panel.

   * @param row number of rows
   * @param col number of cols
   */
  public DungeonPanel(int row, int col) {
    this.setBackground(Color.WHITE);
    this.setSize(500, 500);
    this.setVisible(true);
    this.board = new Location[row][col];
  }
  
  /**
   * set the board.

   * @param board board
   */
  public void setBoard(Location[][] board) {
    this.board = board;
    this.repaint();
  }
  
  @Override
  public void paintComponent(Graphics g) {
    if (this.board.length == 1 & this.board[0].length == 1) {
      return;
    }
    
    int row = this.board.length;
    int col = this.board[0].length;
    
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        Location curr = this.board[i][j];
        if (!curr.isVisited()) {
          continue;
        }
        if (curr.getNorth()) {
          g.fillRect(j * 70 + 20, i * 70, 2, 20);
          g.fillRect(j * 70 + 40, i * 70, 2, 20);
        } else {
          g.fillRect(j * 70 + 20, i * 70 + 20, 20, 2);
        }
        if (curr.getSouth()) {
          g.fillRect(j * 70 + 20, i * 70 + 40, 2, 20);
          g.fillRect(j * 70 + 40, i * 70 + 40, 2, 20);
        } else {
          g.fillRect(j * 70 + 20, i * 70 + 40, 20, 2);
        }
        if (curr.getEast()) {
          g.fillRect(j * 70 + 40, i * 70 + 20, 20, 2);
          g.fillRect(j * 70 + 40, i * 70 + 40, 20, 2);
        } else {
          g.fillRect(j * 70 + 40, i * 70 + 20, 2, 20);
        }
        if (curr.getWest()) {
          g.fillRect(j * 70, i * 70 + 20, 20, 2);
          g.fillRect(j * 70, i * 70 + 40, 20, 2);
        } else {
          g.fillRect(j * 70 + 20, i * 70 + 20, 2, 20);
        }
        
        if (curr.isHere()) {
          g.fillOval(j * 70 + 20, i * 70 + 20, 20, 20);
        }
      }
    }


  }

}
