import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import duration.Duration;
import duration.HmsDuration;
import org.junit.Before;
import org.junit.Test;

/**
 * Class for testing the Duration class.
 */
public class DurationTest {

  private Duration ten29m58s;

  @Before
  public void setUp() {
    ten29m58s = hms(10, 29, 58);
  }

  /**
   * This method is providing short-hand way of creating instances of a new
   * duration object. We will see how we can leverage this methodology to gaining
   * more flexibility with our implementation in the next Module.
   * 
   * @param hours   the initial hours.
   * @param minutes the initial minutes
   * @param seconds the initial seconds
   * @return a new instance of a duration object
   */
  protected Duration hms(int hours, int minutes, int seconds) {
    return new HmsDuration(hours, minutes, seconds);
  }

  @Test
  public void testAsHms() {
    String expectedValue = "10:29:58";
    assertEquals(expectedValue, ten29m58s.asHms());

    // The first parameter of assertEquals is printed when the assert fails.
    // It should be used to tell the user which of these fails.
    assertEquals("Small duration", "1:23:45", hms(1, 23, 45).asHms());
    assertEquals("Medium duration with zero seconds", "22:03:00", hms(22, 3, 0).asHms());
    assertEquals("Large duration with zeron minutes", "457:00:03", hms(457, 0, 3).asHms());
    assertEquals("Duration with zero hours", "0:10:00", hms(0, 10, 0).asHms());
    assertEquals("Constructor carries seconds", "2:34:08", hms(2, 33, 68).asHms());
    assertEquals("Constructor carries minutes", "2:33:45", hms(1, 93, 45).asHms());
    assertEquals("Constructor carries both", "4:34:34", hms(2, 33, 7294).asHms());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidHours() {
    hms(-1, 23, 45);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidMinutes() {
    hms(1, -23, 45);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidSeconds() {
    hms(1, 23, -45);
  }

  @Test
  public void testInSeconds() {
    assertEquals("small duration", 5025L, hms(1, 23, 45).inSeconds());
    assertEquals("medium duration with zero seconds", 79380L, hms(22, 3, 0).inSeconds());
    assertEquals("large duration with zero minutes", 1645203L, hms(457, 0, 3).inSeconds());
    assertEquals("duration with zero hours", 600L, hms(0, 10, 0).inSeconds());
    assertEquals("zero duration", 0L, hms(0, 0, 0).inSeconds());
  }

  @Test
  public void testEquals() {
    assertTrue(ten29m58s.equals(ten29m58s));
    assertTrue(ten29m58s.equals(new HmsDuration(ten29m58s.inSeconds())));
    assertFalse(hms(1, 23, 45).equals(hms(1, 23, 44)));
  }

  @Test
  public void testHashCode() {
    assertEquals(Long.hashCode(hms(1, 23, 45).inSeconds()), hms(1, 23, 45).hashCode());
  }

  @Test
  public void testPlus() {
    assertEquals("small values", hms(2, 43, 55), hms(1, 23, 45).plus(hms(1, 20, 10)));
    assertEquals("commutative property", hms(2, 43, 55), hms(1, 20, 10).plus(hms(1, 23, 45)));
    assertEquals("carries seconds", hms(3, 38, 18), hms(1, 23, 33).plus(hms(2, 14, 45)));
    assertEquals("carries minutes", hms(4, 19, 48), hms(1, 23, 3).plus(hms(2, 56, 45)));
    assertEquals("carries both", hms(4, 20, 18), hms(1, 23, 33).plus(hms(2, 56, 45)));
  }
}
