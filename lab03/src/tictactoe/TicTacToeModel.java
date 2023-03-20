package tictactoe;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Implements the interface TicTacToe. Generate a model of the game.

 * @author ZongyuWu
 *
 */
public class TicTacToeModel implements TicTacToe {
  // add your implementation here
  
  private Player[][] board;
  private boolean turn;
  private Player winner;
  private int remain;
  
  /**
   * Set up the board, first turn and remain space of a new game.
   */
  public TicTacToeModel() {
    this.board = new Player[3][3];
    this.turn = false;
    this.remain = 9;
  }
  
  @Override
  public void move(int r, int c) {
    if (isGameOver()) {
      throw new IllegalStateException("The game is already over");
    }
    if (r < 0 | r > 2 | c < 0 | c > 2) {
      throw new IllegalArgumentException("This position is invalid.");
    }
    if (this.board[r][c] == Player.O | this.board[r][c] == Player.X) {
      throw new IllegalArgumentException("This position is occupied.");
    }
    
    Player p = getTurn();
    this.board[r][c] = p;
    
    this.remain -= 1;
    changePlayer(p);
    if (checkWin(r, c)) {
      this.winner = p;
    }
  }
  
  /**
   * Change the player of the next turn.

   * @param p the current player.
   */
  private void changePlayer(Player p) {
    if (p == Player.O) {
      this.turn = false;
    } else {
      this.turn = true;
    }
  }
  
  /**
   * Check if there's a winner after a move at row r, column c.

   * @param r the row of the current move.
   * @param c the column of the current move.
   * @return true if there's a winner, otherwise false.
   */
  private boolean checkWin(int r, int c) {
    if (r == c) {
      if ((this.board[0][0] == this.board[1][1]) & (this.board[1][1] == this.board[2][2])) {
        return true;
      } 
    } 
    
    if (r + c == 2) {
      if ((this.board[0][2] == this.board[1][1]) & (this.board[1][1] == this.board[2][0])) {
        return true;
      }
    }
    
    if ((this.board[r][0] == this.board[r][1]) & (this.board[r][1] == this.board[r][2])) {
      return true;
    }
    
    if ((this.board[0][c] == this.board[1][c]) & (this.board[1][c] == this.board[2][c])) {
      return true;
    }
    
    return false;
  }
  
  @Override
  public Player getTurn() {
    if (this.turn == false) {
      return Player.X;
    } else {
      return Player.O;
    }
  }
  
  @Override
  public boolean isGameOver() {
    if ((this.winner != null) | (this.remain == 0)) {
      return true;
    }
    return false;
  }
  
  @Override
  public Player getWinner() {
    return this.winner;
  }
  
  @Override
  public Player[][] getBoard() {
    Player[][] b = new Player[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (this.board[i][j] != null) {
          b[i][j] = this.board[i][j]; 
        }
      }
    }
    return b;
  }
  
  @Override
  public Player getMarkAt(int r, int c) {
    if (r < 0 | r > 2 | c < 0 | c > 2) {
      throw new IllegalArgumentException("This position is invalid.");
    }
    return this.board[r][c];
  }
  
  @Override
  public String toString() {
    // Using Java stream API to save code:
    return Arrays.stream(getBoard()).map(
      row -> " " + Arrays.stream(row).map(
        p -> p == null ? " " : p.toString()).collect(Collectors.joining(" | ")))
          .collect(Collectors.joining("\n-----------\n"));
    // This is the equivalent code as above, but using iteration, and still using the helpful
    // built-in String.join method.
    // List<String> rows = new ArrayList<>();
    // for(Player[] row : getBoard()) {
    //   List<String> rowStrings = new ArrayList<>();
    //   for(Player p : row) {
    //     if(p == null) {
    //       rowStrings.add(" ");
    //     } else {
    //       rowStrings.add(p.toString());
    //     }
    //   }
    //   rows.add(" " + String.join(" | ", rowStrings));
    // }
    // return String.join("\n-----------\n", rows);
  }
}
