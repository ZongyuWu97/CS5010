package tictactoe;

import java.io.IOException;
import java.util.Scanner;

/**
 * This starter files is for students to implement a console controller for the
 * TicTacToe MVC assignment.
 */
public class TicTacToeConsoleController implements TicTacToeController {

  private final Appendable out;
  private final Scanner scan;

  /**
   * Constructor for the controller.

   * @param in  the source to read from
   * @param out the target to print to
   */
  public TicTacToeConsoleController(Readable in, Appendable out) {
    if (in == null || out == null) {
      throw new IllegalArgumentException("Readable and Appendable can't be null");
    }
    this.out = out;
    scan = new Scanner(in);
  }

  @Override
  public void playGame(TicTacToe m) {
    if (m == null) {
      throw new IllegalArgumentException("Null model.");
    }
    
    while (scan.hasNext()) {
      String x = scan.next();
      try {
        if ("q".equals(x) || "Q".equals(x)) {
          this.out.append("Game quit! Ending game state:\n" + m.toString() + "\n");
          return;
        }
        if (!isInt(x)) {
          this.out.append("Not a valid number: " + x + "\n");
          continue;
        }
      } catch (IOException ioe) {
        throw new IllegalStateException("Append failed", ioe);
      }
        
      String y = scan.next();
      try {
        if ("q".equals(y) || "Q".equals(y)) {
          this.out.append("Game quit! Ending game state:\n" + m.toString() + "\n");
          return;
        }
        if (!isInt(y)) {
          this.out.append("Not a valid number: " + y + "\n");
          continue;
        }
      } catch (IOException ioe) {
        throw new IllegalStateException("Append failed", ioe);
      }
      
      int r = Integer.parseInt(x) - 1;
      int c = Integer.parseInt(y) - 1;
      try {
        if (r < 0 || r > 2 || c < 0 || c > 2 || m.getMarkAt(r, c) != null) {
          this.out.append("Not a valid move: " + x + ", " + y + "\n");
          continue;
        }
        m.move(r, c);
        this.out.append(m.toString() + "\n");
        if (m.isGameOver()) {
          String winner;
          if (!(m.getWinner() == null)) {
            winner = m.getWinner().toString() + " wins.";
          } else {
            winner = "Tie game.";
          }
          this.out.append("Game is over! " + winner + "\n");
          return;
        }
        newMove(m);
      } catch (IOException ioe) {
        throw new IllegalStateException("Append failed", ioe);
      }
    }
    scan.close();
  }
  
  private boolean isInt(String str) {
    for (int i = 0; i < str.length(); i++) {
      if (!Character.isDigit(str.charAt(i))) {
        return false;
      }
    }
    return true;
  }
  
  private void newMove(TicTacToe m) {
    try {
      this.out.append("Enter a move for " + m.getTurn().toString() + ":\n");
    } catch (IOException ioe) {
      throw new IllegalStateException("Append failed", ioe);
    }
  }

}
