import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import sanctuary.Food;
import sanctuary.Housing;
import sanctuary.JungleFriendsHousing;
import sanctuary.Monkey;
import sanctuary.Sex;
import sanctuary.Size;
import sanctuary.Species;

/**
 * Test functionality of JungleFriendsHousing class.

 * @author ZongyuWu
 *
 */
public class JungleFriendsHousingTest {
  
  private Housing iso = new JungleFriendsHousing("Isolation");
  private Housing enc = new JungleFriendsHousing("Enclosure", 10, Species.Drill);
  private final Monkey monkey = new Monkey("name", Species.Drill, 
      Sex.Female, Size.Large, 10, Food.Eggs, 20);

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor1() {
    new JungleFriendsHousing("any", 10, Species.Drill);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor2() {
    new JungleFriendsHousing("Isolation", 1, Species.Drill);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor3() {
    new JungleFriendsHousing("Enclosure");
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor4() {
    new JungleFriendsHousing("Enclosure", -1, Species.Drill);
  }

  @Test
  public void testAddMonkey() {
    Housing tmp = new JungleFriendsHousing("Isolation");
    tmp.addMonkey(this.monkey);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddMonkey1() {
    Housing tmp = new JungleFriendsHousing("Isolation");
    tmp.addMonkey(this.monkey);
    tmp.addMonkey(this.monkey);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddMonkey2() {
    Housing tmp = new JungleFriendsHousing("Enclosure", 1, Species.Drill);
    tmp.addMonkey(this.monkey);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddMonkey3() {
    Housing tmp = new JungleFriendsHousing("Enclosure", 100, Species.Guereza);
    tmp.addMonkey(this.monkey);
  }
  
  @Test
  public void testAddMonkey4() {
    Housing tmp = new JungleFriendsHousing("Enclosure", 100, Species.Drill);
    tmp.addMonkey(this.monkey);
    assertEquals(monkey, tmp.getResidents().get(0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveMonkey1() {
    this.iso.removeMonkey(this.monkey);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveMonkey2() {
    this.enc.removeMonkey(monkey);
  }
  
  @Test
  public void testRemoveMonkey3() {
    Housing tmp = new JungleFriendsHousing("Isolation");
    tmp.addMonkey(this.monkey);
    tmp.removeMonkey(this.monkey);
    assertTrue(tmp.isEmpty());
  }

  @Test
  public void testRemoveMonkey4() {
    Housing tmp = new JungleFriendsHousing("Enclosure", 100, Species.Drill);
    tmp.addMonkey(this.monkey);
    tmp.removeMonkey(this.monkey);
    assertTrue(tmp.isEmpty());
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testPopMonkey1() {
    this.iso.popMonkey();
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testPopMonkey2() {
    this.enc.popMonkey();
  }

  @Test
  public void testPopMonkey3() {
    Housing tmp = new JungleFriendsHousing("Isolation");
    tmp.addMonkey(this.monkey);
    assertEquals(this.monkey, tmp.popMonkey());
    assertTrue(tmp.isEmpty());
  }

  @Test
  public void testPopMonkey4() {
    Housing tmp = new JungleFriendsHousing("Enclosure", 100, Species.Drill);
    tmp.addMonkey(this.monkey);
    assertEquals(this.monkey, tmp.popMonkey());
    assertTrue(tmp.isEmpty());
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testShowSign1() {
    this.iso.showSign();
  }
  
  @Test
  public void testShowSign2() {
    Housing tmp = new JungleFriendsHousing("Enclosure", 100, Species.Drill);
    tmp.addMonkey(this.monkey);
    String[] sign = tmp.showSign();
    
    assertEquals(monkey.getName() + monkey.getSex() + monkey.getFood(), sign[0]);
  }
  
  @Test
  public void testGetType1() {
    assertEquals("Isolation", this.iso.getType());
  }

  @Test
  public void testGetType2() {
    assertEquals("Enclosure", this.enc.getType());
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testGetSize1() {
    this.iso.getSize();
  }

  @Test
  public void testGetSize2() {
    assertEquals(10, this.enc.getSize());
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testGetSpecies1() {
    this.iso.getSpecies();
  }
  
  @Test
  public void testGetSpecies2() {
    assertEquals(Species.Drill, this.enc.getSpecies());
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testSetSpecies1() {
    Housing tmp = new JungleFriendsHousing("Enclosure", 100, Species.Drill);
    tmp.addMonkey(this.monkey);
    tmp.setSpecies(Species.Guereza);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testSetSpecies2() {
    this.iso.setSpecies(Species.Drill);
  }
  
  @Test
  public void testSetSpecies3() {
    Housing tmp = new JungleFriendsHousing("Enclosure", 100, Species.Drill);
    tmp.setSpecies(Species.Guereza);
  }
  
  @Test
  public void testGetResidents1() {
    assertTrue(this.iso.getResidents().isEmpty());
  }
  
  @Test
  public void testGetResidents2() {
    Housing tmp = new JungleFriendsHousing("Enclosure", 100, Species.Drill);
    tmp.addMonkey(this.monkey);
    assertEquals(this.monkey, tmp.getResidents().get(0));
  }
  
  @Test
  public void testIsEmpty1() {
    assertTrue(this.iso.isEmpty());
    assertTrue(this.enc.isEmpty());
  }
  
  @Test
  public void testIsEmpty2() {
    Housing tmp = new JungleFriendsHousing("Enclosure", 100, Species.Drill);
    tmp.addMonkey(monkey);
    assertFalse(tmp.isEmpty());
  }

}
