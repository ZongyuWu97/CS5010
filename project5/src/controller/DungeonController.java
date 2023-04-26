package controller;

/**
 * Represents the interface of a controller.

 * @author ZongyuWu
 *
 */
public interface DungeonController {
  
  /**
   * Begin game.
   */
  public void playGame();
  
  /**
   * Start the game with specified config.

   * @param wrap wrapping or non-wrapping
   * @param row number of rows
   * @param col number of columns
   * @param interconnectivity interconnectivity
   * @param percentage percentage of treasure and arrow
   * @param numMonster number of monster in the dungeon
   */
  public void startGame(String wrap, int row, int col,
      int interconnectivity, int percentage, int numMonster);
  
  /**
   * Restart with the same random seed.
   */
  public void restart();
  
  /**
   * Move to the direction.

   * @param direction direction
   */
  public void move(String direction);
  
  /**
   * Player pick up treasure and arrow.
   */
  public void pick();
  
  /**
   * Player shoot to direction with distance.

   * @param direction direction
   * @param distance distance
   */
  public void shoot(String direction, int distance);
  
  /**
   * If the player wins.

   * @return win or not
   */
  public boolean isWin();
  
  /**
   * If the player lose.

   * @return lose or not
   */
  public boolean isLoose();
  
  /**
   * If the player can shoot now.

   * @return can shoot or not
   */
  public boolean canShoot();

}
