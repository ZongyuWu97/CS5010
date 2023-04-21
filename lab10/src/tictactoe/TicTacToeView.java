package tictactoe;

/**
 * A view for TicTacToe: display the game board and provide visual interface
 * for users.
 */
public interface TicTacToeView {

  /**
   * Set up the controller to handle click events in this view.

   * @param listener the controller
   */
  void addClickListener(TicTacToeController listener);

  /**
   * Refresh the view to reflect any changes in the game state.
   */
  void refresh();

  /**
   * Make the view visible to start the game session.
   */
  void makeVisible();
}
