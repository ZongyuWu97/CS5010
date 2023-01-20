package transmission;

/**
 * Transmissions are represented by the speed a car is traveling and the gear
 * that the car is in when it is traveling that speed.
 */
public interface Transmission {

  /**
   * Increases the speed of this Transmission by 1 MPH.
   */
  void increaseSpeed();

  /**
   * Decreases the speed of this Transmission by 1 MPH.
   *
   * @throws IllegalStateException if calling this method would cause the speed to go below 0
   */
  void decreaseSpeed() throws IllegalStateException;

  /**
   * Gets the speed of this Transmission.
   * 
   * @return the speed
   */
  int getSpeed();

  /**
   * Gets the gear of this Transmission.
   * 
   * @return the gear
   */
  int getGear();
}
