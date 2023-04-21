package util;

public interface Monster {
  
  /**
   * Get health of the monster. 2 is full, 1 is injured, 0 is dead.

   * @return health of monster
   */
  public int getHealth();
  
  /**
   * 
   * @return
   */
  public boolean isDead();
  
  /**
   * Set monster's health to health.

   * @param health health
   */
  public void setHealth(int health);

}
