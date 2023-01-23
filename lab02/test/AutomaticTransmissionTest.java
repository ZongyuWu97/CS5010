import static org.junit.Assert.assertEquals;

import org.junit.Test;
import transmission.AutomaticTransmission;
import transmission.Transmission;


/**
 * Test the AutomaticTransmission class.

 * @author ZongyuWu
 *
 */
public class AutomaticTransmissionTest {
  
  private Transmission auto(int speed1, int speed2, int speed3, int speed4, int speed5) {
    return new AutomaticTransmission(speed1, speed2, speed3, speed4, speed5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAutomaticTransmission1() {
    auto(10, 5, 30, 40, 50);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAutomaticTransmission2() {
    auto(10, 20, 5, 40, 50);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testAutomaticTransmission3() {
    auto(10, 20, 30, 5, 50);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAutomaticTransmission4() {
    auto(10, 20, 30, 40, 5);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testAutomaticTransmission5() {
    auto(-10, 20, 30, 40, 50);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAutomaticTransmission6() {
    auto(10, -20, 530, 40, 50);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testAutomaticTransmission7() {
    auto(10, 20, -30, 40, 50);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAutomaticTransmission8() {
    auto(10, 20, 30, -40, 50);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAutomaticTransmission9() {
    auto(10, 20, 30, 40, -50);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testAutomaticTransmission10() {
    auto(0, 20, 30, 40, 50);
  }

  @Test
  public void testGetSpeed1() {
    Transmission temp = auto(10, 20, 30, 40, 50); 
    assertEquals(0, temp.getSpeed());
  }

  @Test
  public void testGetSpeed2() {
    Transmission temp = auto(10, 20, 30, 40, 50); 
    for (int i = 0; i < 10; i++) {
      temp.increaseSpeed();
    }
    assertEquals(10, temp.getSpeed());
  }

  @Test
  public void testGetGear1() {
    Transmission temp = auto(10, 20, 30, 40, 50); 
    assertEquals(0, temp.getGear());
  }

  @Test
  public void testGetGear2() {
    Transmission temp = auto(10, 20, 30, 40, 50); 
    
    for (int i = 0; i < 10; i++) {
      temp.increaseSpeed();
    }
    
    assertEquals(2, temp.getGear());
  }
  
  @Test
  public void testIncreaseSpeed() {
    int speed = (int) (Math.random() * 1000);
    Transmission temp = auto(10, 20, 30, 40, 50); 
    
    for (int i = 0; i < speed; i++) {
      temp.increaseSpeed();
    }
    
    assertEquals(speed, temp.getSpeed());
  }
  
  @Test
  public void testDecreaseSpeed1() {
    int speed = (int) (Math.random() * 1000);
    Transmission temp = auto(10, 20, 30, 40, 50); 
    
    for (int i = 0; i < speed; i++) {
      temp.increaseSpeed();
    }
    
    for (int i = 0; i < speed - 2; i++) {
      temp.decreaseSpeed();
    }    
    
    assertEquals(2, temp.getSpeed());
  }  
  
  @Test(expected = IllegalStateException.class)
  public void testDecreaseSpeed2() {
    Transmission temp = auto(10, 20, 30, 40, 50); 
    
    temp.decreaseSpeed();    
  }  
  
  @Test
  public void testToString1() {
    String s = "Transmission (speed = 0, gear = 0)";
    Transmission temp = auto(10, 20, 30, 40, 50); 
    assertEquals(s, temp.toString());
  }
  
  @Test
  public void testToString2() {
    String s = "Transmission (speed = 45, gear = 3)";
    Transmission temp = auto(10, 40, 50, 60, 70); 
    
    for (int i = 0; i < 45; i++) {
      temp.increaseSpeed();
    }
    
    assertEquals(s, temp.toString());
  }
}
