package tictactoe;

/**
 * Represents a Controller for TicTacToe: handle user moves by executing them
 * using the model; convey move outcomes to the user in some form.
 */
public interface TicTacToeController {

  /**
   * Execute a single game of TicTacToe given a TicTacToe Model. When the game
   * is over, the playGame method ends.
   *
   */ 
  void playGame();

  /**
   * Handle an action in a single cell of the board, such as to make a move.
   *
   * @param row the row of the clicked cell
   * @param col the column of the clicked cell
   */
  void handleCellClick(int row, int col);
}
