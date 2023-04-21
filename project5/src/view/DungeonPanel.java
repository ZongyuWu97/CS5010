package view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import util.Location;

public class DungeonPanel extends JPanel {

  private static final long serialVersionUID = 1L;

  private Location[][] board;
  
  /**
   * Constructor of a panel.
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
    int row = this.board.length;
    int col = this.board[0].length;
    
    


  }

}
