package util;

/**
 * Interface for monster.

 * @author ZongyuWu
 *
 */
public interface Monster {
  
  /**
   * Get health of the monster. 2 is full, 1 is injured, 0 is dead.

   * @return health of monster
   */
  public int getHealth();
  
  /**
   * Is dead or not.

   * @return dead or not
   */
  public boolean isDead();
  
  /**
   * Set monster's health to health.

   * @param health health
   */
  public void setHealth(int health);

}
