package model;

/**
 * Represents the interface of a controller.

 * @author ZongyuWu
 *
 */
public interface DungeonController {
  
  public void startGame(int percentage, int numMonster);
  
  public void move(String direction);
  
  public void pick();
  
  public void shoot(String direction, int distance);
  
  public void locationInformation();
  
  public void playeInformation();
  
  public boolean isWin();
  
  public boolean isLoose();
  
  public boolean canShoot();
}
