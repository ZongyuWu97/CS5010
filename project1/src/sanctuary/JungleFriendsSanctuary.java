package sanctuary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Represents a single sanctuary. Contains isolations and enclosures.

 * @author ZongyuWu
 *
 */
public class JungleFriendsSanctuary implements Sanctuary {

  private List<Housing> isolations;
  private List<Housing> enclosures;
  
  /**
   * Constructor of a sanctuary instance.

   * @param numIsolation Number of isolation in the sanctuary.
   * @param numEnclosure Number of enclosure in the sanctuary.
   * @param enclosureSize Size of each enclosure.
   * @param enclosureSpecies Species of each enclosure.
   */
  public JungleFriendsSanctuary(int numIsolation, int numEnclosure, 
      int[] enclosureSize, Species[] enclosureSpecies) {
    if (numIsolation <= 0 | numEnclosure <= 0) {
      throw new IllegalArgumentException("Number of isolation and enclosure "
          + "should be non-negative.");
    }
    if (numEnclosure != enclosureSize.length | numEnclosure != enclosureSpecies.length) {
      throw new IllegalArgumentException("Number of enclosure size and species incompatible.");
    }

    this.isolations = new ArrayList<Housing>();
    this.enclosures = new ArrayList<Housing>();
    
    for (int i = 0; i < numIsolation; i++) {
      this.isolations.add(new JungleFriendsHousing("Isolation"));
    }
    for (int i = 0; i < numEnclosure; i++) {
      Housing enclosure = new JungleFriendsHousing("Enclosure", 
          enclosureSize[i], enclosureSpecies[i]);
      this.enclosures.add(enclosure);
      
    }
  }
  
  /**
   * Constructor of a sanctuary instance.
   */
  public JungleFriendsSanctuary() {
    this.isolations = new ArrayList<Housing>();
    this.enclosures = new ArrayList<Housing>();

    for (int i = 0; i < 5; i++) {
      this.isolations.add(new JungleFriendsHousing("Isolation"));
    }
    
    for (Species species : Species.values()) {
      this.enclosures.add(new JungleFriendsHousing("Enclosure", 50, species));
    }
  }
  
  @Override
  public void increaseIsolation() {
    this.isolations.add(new JungleFriendsHousing("Isolation"));
  }

  @Override
  public void increaseEnclosure(int size, Species species) {
    if (size <= 0) {
      throw new IllegalArgumentException("Size must be positive.");
    }
    this.enclosures.add(new JungleFriendsHousing("Enclosure", size, species));
  }

  @Override
  public SpeciesLocation report() {
    HashMap<Species, List<Housing>> repo = new HashMap<Species, List<Housing>>();
    for (Housing enclosure : this.enclosures) {
      if (!enclosure.isEmpty()) {
        List<Housing> tmp = repo.getOrDefault(enclosure.getSpecies(), new ArrayList<Housing>());
        tmp.add(enclosure);
        repo.put(enclosure.getSpecies(), tmp);
      }
    }
    for (Housing isolation : this.isolations) {
      if (!isolation.isEmpty()) {
        Monkey monkey = isolation.getResidents().get(0);
        List<Housing> tmp = repo.getOrDefault(monkey.getSpecies(), new ArrayList<Housing>());
        tmp.add(isolation);
        repo.put(monkey.getSpecies(), tmp);      
      }
    }

    List<Species> speciesList = new ArrayList<>();
    List<List<Housing>> locationList = new ArrayList<>();
    for (Species species : Species.values()) {
      if (repo.containsKey(species)) {
        speciesList.add(species);
        locationList.add(repo.get(species));
      }
    }
    
    return new SpeciesLocation(speciesList, locationList);
  }

  @Override
  public List<Housing> lookFor(Species species) {
    List<Housing> res = new ArrayList<>();
    for (Housing enc : this.enclosures) {
      if (!enc.isEmpty()) {
        if (enc.getSpecies() == species) {
          res.add(enc);
        }
      }
    }
    for (Housing iso : this.isolations) {
      if (!iso.isEmpty()) {
        if (iso.getResidents().get(0).getSpecies() == species) {
          res.add(iso);
        }
      }
    }
      
    if (res.size() == 0) {
      System.out.println("This species is not housed.");
    }
    return res;
  }

  @Override
  public List<NameLocation> nameList() {
    List<NameLocation> res = new ArrayList<>();
    for (Housing iso : this.isolations) {
      if (!iso.getResidents().isEmpty()) {
        NameLocation nl = new NameLocation(iso.getResidents().get(0).getName(), iso);
        res.add(nl);
      }
    }
    for (Housing enc : this.enclosures) {
      for (Monkey monkey : enc.getResidents()) {
        NameLocation nl = new NameLocation(monkey.getName(), enc);
        res.add(nl);
      }
    }
    
    Collections.sort(res);
    return res;
  }

  @Override
  public List<Item> shoppingList() {
    HashMap<Food, Integer> list = new HashMap<>();
    
    for (Housing iso : this.isolations) {
      if (!iso.getResidents().isEmpty()) {
        Monkey monkey = iso.getResidents().get(0);
        list.put(monkey.getFood(), list.getOrDefault(monkey.getFood(), 0) + monkey.getFoodInt());
      }
    }
    for (Housing enc : this.enclosures) {
      for (Monkey monkey : enc.getResidents()) {
        list.put(monkey.getFood(), list.getOrDefault(monkey.getFood(), 0) + monkey.getFoodInt());
      }
    }    
    
    List<Item> res = new ArrayList<>();
    for (Food food : Food.values()) {
      if (list.containsKey(food)) {
        res.add(new Item(food, list.get(food)));
      }
    }
    
    return res;
  }
  
  @Override
  public List<Housing> getIsolations() {
    return this.isolations;
  }
  
  @Override
  public List<Housing> getEnclosures() {
    return this.enclosures;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    for (Housing iso : this.isolations) {
      hash += iso.hashCode();
    }
    for (Housing enc : this.enclosures) {
      hash += enc.hashCode();
    } 
    return hash;
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof JungleFriendsSanctuary)) {
      return false;
    }
    return this.hashCode() == o.hashCode();
  }

}
