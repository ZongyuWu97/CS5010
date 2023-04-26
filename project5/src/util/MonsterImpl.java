package util;

/**
 * Implements the Monster interface.

 * @author ZongyuWu
 *
 */
public class MonsterImpl implements Monster {
  
  private int health;
  
  public MonsterImpl() {
    this.health = 2;
  }
  
  public int getHealth() {
    return this.health;
  }
  
  public boolean isDead() {
    return this.health == 0;
  }

  @Override
  public void setHealth(int health) {
    this.health = health;
  }

}
