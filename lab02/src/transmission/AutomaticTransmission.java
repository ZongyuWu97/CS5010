package transmission;

/**
 * Automatically determine the gear when change speed.

 * @author ZongyuWu
 *
 */
public class AutomaticTransmission implements Transmission {
  private final int thre1;
  private final int thre2;
  private final int thre3;
  private final int thre4;
  private final int thre5;
  private int speed = 0;
  
  /**
   * Constructs a transmission object with its speed threshold.

   * @param speed1 1st threshold.
   * @param speed2 2nd threshold.
   * @param speed3 3rd threshold.
   * @param speed4 4th threshold.
   * @param speed5 5th threshold.
   * @throws thresholds should be increasing and non-negative.
   */
  public AutomaticTransmission(int speed1, int speed2, int speed3, int speed4, int speed5) {
    if (speed1 >= speed2 | speed2 >= speed3 | speed3 >= speed4 | speed4 >= speed5) {
      throw new IllegalArgumentException("Threshold shoud increase.");
    }
    if (speed1 < 0 | speed2 < 0 | speed3 < 0 | speed4 < 0 | speed5 < 0) {
      throw new IllegalArgumentException("Threshold cannot be negative.");
    }
    if (speed1 == 0) {
      throw new IllegalArgumentException("First threshold should be positive.");
    }
    
    this.thre1 = speed1;
    this.thre2 = speed2;
    this.thre3 = speed3;
    this.thre4 = speed4;
    this.thre5 = speed5;
  }

  @Override
  public void increaseSpeed() {
    this.speed += 1;
  }

  @Override
  public void decreaseSpeed() throws IllegalStateException {
    if (this.speed - 1 < 0) {
      throw new IllegalStateException("Speed cannot be negative.");
    } else {
      this.speed -= 1;
    }
  }

  @Override
  public int getSpeed() {
    return this.speed;
  }

  @Override
  public int getGear() {
    if (this.speed == 0) {
      return 0;
    } else if (this.speed < thre1) {
      return 1;
    } else if (this.speed < thre2) {
      return 2;
    } else if (this.speed < thre3) {
      return 3;
    } else if (this.speed < thre4) {
      return 4;
    } else if (this.speed < thre5) {
      return 5;
    } else {
      return 6;
    }
  }
  
  @Override
  public String toString() {
    return String.format("Transmission (speed = %d, gear = %d)", this.getSpeed(), this.getGear());
  }
  
  @Override
  public int hashCode() {
    String s = Integer.toString(this.thre1) + Integer.toString(this.thre2) 
        + Integer.toString(this.thre3) + Integer.toString(this.thre4) 
        + Integer.toString(this.thre5);
    return Integer.parseInt(s);
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
      
    if (!(o instanceof AutomaticTransmission)) {
      return false;
    }
    
    return this.hashCode() == o.hashCode();
  }

}
