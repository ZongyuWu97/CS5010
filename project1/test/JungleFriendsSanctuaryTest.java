
import static org.junit.Assert.assertEquals;

import java.util.List;
import org.junit.Test;
import sanctuary.Food;
import sanctuary.Item;
import sanctuary.JungleFriendsSanctuary;
import sanctuary.Monkey;
import sanctuary.NameLocation;
import sanctuary.Sanctuary;
import sanctuary.Sex;
import sanctuary.Size;
import sanctuary.Species;
import sanctuary.SpeciesLocation;

/**
 * Test functionality of the JungleFriendsSanctuary class.

 * @author ZongyuWu
 *
 */
public class JungleFriendsSanctuaryTest {
  private final Monkey monkey = new Monkey("name", Species.Drill, 
      Sex.Female, Size.Large, 10, Food.Eggs, 20);

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor1() {
    new JungleFriendsSanctuary(0, 2, new int[] {5, 5}, 
        new Species[] {Species.Drill, Species.Guereza});
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor2() {
    new JungleFriendsSanctuary(1, 0, new int[] {5, 5}, 
        new Species[] {Species.Drill, Species.Guereza});
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor3() {
    new JungleFriendsSanctuary(1, 2, new int[] {5}, 
        new Species[] {Species.Drill, Species.Guereza});
  }
  
  @Test
  public void testIncreaseIsolation() {
    Sanctuary tmp = new JungleFriendsSanctuary();
    tmp.increaseIsolation();
  }
  
  @Test
  public void testIncreaseEnclosure1() {
    Sanctuary tmp = new JungleFriendsSanctuary();
    tmp.increaseEnclosure(5, Species.Drill);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIncreaseEnclosure2() {
    Sanctuary tmp = new JungleFriendsSanctuary();
    tmp.increaseEnclosure(-1, Species.Drill);
  }
  
  @Test
  public void testReport() {
    Sanctuary tmp = new JungleFriendsSanctuary();
    tmp.getIsolations().get(0).addMonkey(this.monkey);
    SpeciesLocation report = tmp.report();
    
    assertEquals(report.species.get(0), this.monkey.getSpecies());
    assertEquals(report.location.get(0).get(0), tmp.lookFor(this.monkey.getSpecies()).get(0));
  }
  
  @Test
  public void testLookFor() {
    Sanctuary tmp = new JungleFriendsSanctuary();
    tmp.getIsolations().get(0).addMonkey(this.monkey);

    assertEquals(tmp.getIsolations().get(0), tmp.lookFor(this.monkey.getSpecies()).get(0));
  }
  
  @Test
  public void testLookFor1() {
    Sanctuary tmp = new JungleFriendsSanctuary();

    tmp.lookFor(this.monkey.getSpecies());
  }
  
  @Test
  public void testNameList() {
    Sanctuary tmp = new JungleFriendsSanctuary();
    tmp.getIsolations().get(0).addMonkey(this.monkey);
    
    List<NameLocation> namelist = tmp.nameList();
    assertEquals(this.monkey.getName(), namelist.get(0).name);
    assertEquals(tmp.lookFor(this.monkey.getSpecies()).get(0), namelist.get(0).location);
  }
  
  @Test
  public void testShoppingList() {
    Sanctuary tmp = new JungleFriendsSanctuary();
    tmp.getIsolations().get(0).addMonkey(this.monkey);
    
    List<Item> shopList = tmp.shoppingList();
    assertEquals(this.monkey.getFood(), shopList.get(0).name);
    assertEquals(this.monkey.getFoodInt(), shopList.get(0).quantity);
  }

}
