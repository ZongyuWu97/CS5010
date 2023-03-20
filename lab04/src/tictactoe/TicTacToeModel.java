package tictactoe;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Represents a single game of Tic Tac Toe. Three in a row
 * down/across/diagonally to win; X goes first.
 */
public class TicTacToeModel implements TicTacToe {
  private final Player[][] board;
  private Player turn;

  /**
   * Default constructor.
   */
  public TicTacToeModel() {
    board = new Player[3][3];
    turn = Player.X;
  }

  @Override
  public void move(int r, int c) {
    if (isGameOver()) {
      throw new IllegalStateException("Game is over");
    }
    validateRowCol(r, c);
    if (board[r][c] != null) {
      throw new IllegalArgumentException("Position occupied");
    }
    board[r][c] = turn;
    turn = turn == Player.X ? Player.O : Player.X;
  }

  @Override
  public Player getTurn() {
    return turn;
  }

  @Override
  public boolean isGameOver() {
    boolean boardFull = true;
    for (Player[] row : board) {
      if (Arrays.stream(row).anyMatch(Objects::isNull)) {
        boardFull = false;
        break;
      }
    }
    return boardFull || getWinner() != null;
  }

  @Override
  public Player getWinner() {
    for (Player p : Player.values()) {
      // check horizontals
      for (Player[] row : board) {
        if (Arrays.stream(row).allMatch(m -> m == p)) {
          return p;
        }
      }
      // check verticals
      for (int i = 0; i < board[0].length; i++) {
        if (board[0][i] == p && board[1][i] == p && board[2][i] == p) {
          return p;
        }
      }
      // check diagonals
      if (board[0][0] == p && board[1][1] == p && board[2][2] == p) {
        return p;
      }
      if (board[0][2] == p && board[1][1] == p && board[2][0] == p) {
        return p;
      }
    }
    return null;
  }

  @Override
  public Player[][] getBoard() {
    Player[][] ret = new Player[3][3];
    for (int r = 0; r < board.length; r++) {
      ret[r] = Arrays.copyOf(board[r], board[r].length);
    }
    return ret;
  }

  @Override
  public Player getMarkAt(int r, int c) {
    validateRowCol(r, c);
    return board[r][c];
  }

  @Override
  public String toString() {
    // Using Java stream API to save code:
    return Arrays
        .stream(getBoard()).map(row -> " " + Arrays.stream(row)
            .map(p -> p == null ? " " : p.toString()).collect(Collectors.joining(" | ")))
        .collect(Collectors.joining("\n-----------\n"));
    // This is the equivalent code as above, but using iteration, and still using
    // the helpful
    // built-in String.join method.
    // List<String> rows = new ArrayList<>();
    // for(Player[] row : getBoard()) {
    // List<String> rowStrings = new ArrayList<>();
    // for(Player p : row) {
    // if(p == null) {
    // rowStrings.add(" ");
    // } else {
    // rowStrings.add(p.toString());
    // }
    // }
    // rows.add(" " + String.join(" | ", rowStrings));
    // }
    // return String.join("\n-----------\n", rows);
  }
  

  private static void validateRowCol(int r, int c) {
    if (r < 0 || r > 2 || c < 0 || c > 2) {
      throw new IllegalArgumentException("Invalid board position: " + r + "," + c);
    }
  }
}
