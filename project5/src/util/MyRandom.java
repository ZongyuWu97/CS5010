package util;

import java.util.Random;

/**
 * This encapsulate the Random class. Is used for test.

 * @author ZongyuWu
 *
 */
public class MyRandom {
  
  private Random random;
  private boolean randOrNot;
  
  public MyRandom(boolean randOrNot) {
    this.randOrNot = randOrNot;
    random = new Random();
  }
  
  /**
   * Get a random int.

   * @param max max range
   * @return A random integer.
   */
  public int nextInt(int max) {
    if (randOrNot) {
      return this.random.nextInt(max);
    } else {
      return max;
    }
  }
}
