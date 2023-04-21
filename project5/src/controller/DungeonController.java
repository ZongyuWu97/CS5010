package controller;

/**
 * Represents the interface of a controller.

 * @author ZongyuWu
 *
 */
public interface DungeonController {
  
  public void startGame(String wrap, int row, int col,int interconnectivity, int percentage, int numMonster);
  
  public void restart();
  
  public void move(String direction);
  
  public void pick();
  
  public void shoot(String direction, int distance);
  
  public boolean isWin();
  
  public boolean isLoose();
  
  public boolean canShoot();

  public void handleCellClick(String direstion);

}
