import static org.junit.Assert.assertEquals;

import org.junit.Test;
import sanctuary.Food;
import sanctuary.Monkey;
import sanctuary.Sex;
import sanctuary.Size;
import sanctuary.Species;

/**
 * Test functions of Monkey class.

 * @author ZongyuWu
 *
 */
public class MonkeyTest {
  private final Monkey monkey = new Monkey("name", Species.Drill, 
      Sex.Female, Size.Large, 10, Food.Eggs, 20);

  @Test(expected = IllegalArgumentException.class)
  public void testMonkey1() {
    new Monkey("", Species.Drill, Sex.Female, Size.Large,
      10, Food.Eggs, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMonkey2() {
    new Monkey("name", Species.Drill, Sex.Female, Size.Large,
      0, Food.Eggs, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMonkey3() {
    new Monkey("name", Species.Drill, Sex.Female, Size.Large,
      10, Food.Eggs, -1);
  }

  @Test
  public void testGetName() {
    assertEquals("name", monkey.getName());
  }
  
  @Test
  public void testGetSpecies() {
    assertEquals(Species.Drill, monkey.getSpecies());
  }
  
  @Test
  public void testGetSex() {
    assertEquals(Sex.Female, monkey.getSex());
  }
  
  @Test
  public void testGetSize() {
    assertEquals(Size.Large, monkey.getSize());
  }
  
  @Test
  public void testGetSizeInt() {
    assertEquals(10, monkey.getSizeInt());
  }
  
  @Test
  public void testGetWeight() {
    assertEquals(10, monkey.getWeight());
  }
  
  @Test
  public void testGetFood() {
    assertEquals(Food.Eggs, monkey.getFood());
  }
  
  @Test
  public void testGetFoodInt() {
    assertEquals(500, monkey.getFoodInt());
  }
  
  @Test
  public void testGetAge() {
    assertEquals(20, monkey.getAge());
  }

}
