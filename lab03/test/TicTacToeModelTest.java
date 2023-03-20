import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Random;
import org.junit.Before;
import org.junit.Test;
import tictactoe.Player;
import tictactoe.TicTacToe;
import tictactoe.TicTacToeModel;

/**
 * Test methods of the TicTacToe class.

 * @author ZongyuWu
 *
 */
public class TicTacToeModelTest {
  
  private TicTacToe game;
  private TicTacToe full;
  private TicTacToe xboard;
  
  /**
   * Set up an empty game game, a tied game full, and a X wins game xWin.
   */
  @Before
  public void setUp() {
    game = new TicTacToeModel();
    full = new TicTacToeModel();
    xboard = new TicTacToeModel();
    
    full.move(0, 0);
    full.move(0, 1);
    full.move(0, 2);
    full.move(1, 1);
    full.move(1, 0);
    full.move(1, 2);
    full.move(2, 1);
    full.move(2, 0);
    full.move(2, 2);
    
    xboard.move(0, 0);
    xboard.move(0, 1);
    xboard.move(1, 1);
    xboard.move(0, 2);
    xboard.move(2, 2);
  }
  
  @Test
  public void testStarter() {
    assertEquals(Player.X, game.getTurn());
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testMove1() {
    game.move(-1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMove2() {
    TicTacToe tmp = new TicTacToeModel();
    Random random = new Random();
    int r = random.nextInt(3);
    int c = random.nextInt(3);
    
    tmp.move(r, c);
    tmp.move(r, c);
  }
  
  @Test
  public void testMove3() {
    TicTacToe tmp = new TicTacToeModel();
    Player beforePlayer = tmp.getTurn();
    tmp.move(1, 0);
    Player afterPlayer = tmp.getTurn();
    assertFalse(beforePlayer == afterPlayer);
  }
  
  @Test(expected = IllegalStateException.class)
  public void testMove4() {
    full.move(0, 0);
  }
  
  @Test
  public void testGetMarkAt1() {
    TicTacToe tmp = new TicTacToeModel();
    tmp.move(0, 0);
    assertEquals(Player.X, tmp.getMarkAt(0, 0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetMarkAt2() {
    TicTacToe tmp = new TicTacToeModel();
    tmp.getMarkAt(-1, 0);
  }
  
  @Test
  public void testIsGameOver1() {
    assertTrue(full.isGameOver());
  }
  
  @Test
  public void testIsGameOver2() {
    TicTacToe tmp = new TicTacToeModel();
    for (int i = 0; i < 2; i++) {
      tmp.move(i, 0);
      tmp.move(i, 1);
    }
    tmp.move(2, 0);
    assertTrue(tmp.isGameOver());
  }

  @Test
  public void testIsGameOver3() {
    TicTacToe tmp = new TicTacToeModel();
    for (int i = 0; i < 2; i++) {
      tmp.move(0, i);
      tmp.move(1, i);
    }
    tmp.move(0, 2);
    assertTrue(tmp.isGameOver());
  }
  
  @Test
  public void testIsGameOver4() {
    assertFalse(game.isGameOver());
  }
  
  @Test
  public void testIsGameOver5() {
    assertTrue(xboard.isGameOver());
  }
  
  @Test
  public void testGetWinner1() {
    assertEquals(Player.X, xboard.getWinner());
  }

  @Test
  public void testGetWinner2() {
    assertEquals(null, full.getWinner());
  }
  
  @Test
  public void testGetBoard() {
    Player[][] tmp = new Player[3][3];
    tmp[0][0] = Player.X;
    tmp[0][1] = Player.O;
    tmp[1][1] = Player.X;
    tmp[0][2] = Player.O;
    tmp[2][2] = Player.X;
    
    Player[][] b = xboard.getBoard();
    
    for (int r = 0; r < 3; r++) {
      for (int c = 0; c < 3; c++) {
        assertTrue(tmp[r][c] == b[r][c]);
      }
    }
  }
}
