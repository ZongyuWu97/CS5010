import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.NoSuchElementException;
import org.junit.Test;
import permutations.Permutations;

/**
 * Tests for the Permutations class.

 * @author ZongyuWu
 *
 */
public class PermutationsTest {

  @Test(expected = IllegalArgumentException.class)
  public void testInvalid1() {
    new Permutations("124");
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testInvalid2() {
    new Permutations("124", 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalid3() {
    new Permutations("abc", 4);
  }
  
  @Test
  public void testNextHasNext() {
    Permutations p = new Permutations("a");
    assertTrue(p.hasNext());
    assertEquals("a", p.next());
    assertFalse(p.hasNext());
    try {
      p.next();
    } catch (NoSuchElementException e) {
      // Do nothing.
    }
  }
  
  @Test
  public void testPreviousHasPrevious() {
    Permutations p = new Permutations("ab");
    p.next();
    assertTrue(p.hasPrevious());
    assertEquals("a", p.previous());
    assertFalse(p.hasPrevious());
    try {
      p.previous();
    } catch (NoSuchElementException e) {
      // Do nothing.
    }
  }
}
