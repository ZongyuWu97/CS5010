package tictactoe;

/**
 * A single game of Tic Tac Toe, played on a three-by-three grid with two players,
 * with the object of the game to achieve three markers in a row either vertically,
 * horizontally, or diagonally. {@link Player} X goes first.
 */
public interface TicTacToe extends ReadonlyTttModel {

  /**
   * Execute a move in the position specified by the given row and column.
   *
   * @param r the row of the intended move
   * @param c the column of the intended move
   * @throws IllegalArgumentException if the space is occupied or the position is otherwise invalid
   * @throws IllegalStateException if the game is over
   */
  void move(int r, int c);


}
