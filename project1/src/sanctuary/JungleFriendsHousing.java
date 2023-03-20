package sanctuary;

import java.util.ArrayList;
import java.util.List;

/**
 * type represents if it's an Isolation or Enclosure. Isolation has only type.
 * Enclosure also has size and species. residents represents the {@code Monkey}
 * objects in this house.

 * @author ZongyuWu
 *
 */
public class JungleFriendsHousing implements Housing {

  private String type;
  private int size;
  private int remainSize;
  private Species species;
  private List<Monkey> residents;
  
  /**
   * Constructor for Enclosure.

   * @param type Enclosure.
   * @param size positive int.
   * @param species A String.
   */
  public JungleFriendsHousing(String type, int size, Species species) {
    if (!"Isolation".equals(type) && !"Enclosure".equals(type)) {
      throw new IllegalArgumentException("Unknown housing type.");
    }
    if ("Isolation".equals(type)) {
      throw new IllegalArgumentException("Can't specify size and species for isolation.");
    }
    if (size <= 0) {
      throw new IllegalArgumentException("size must be positive.");
    }
    this.type = type;
    this.size = size;
    this.remainSize = size;
    this.species = species;
    this.residents = new ArrayList<Monkey>();
  }
  
  /**
   * Constructor for isolation.

   * @param type Isolation.
   */
  public JungleFriendsHousing(String type) {
    if (!"Isolation".equals(type) & !"Enclosure".equals(type)) {
      throw new IllegalArgumentException("Unknown housing type.");
    }
    if ("Enclosure".equals(type)) {
      throw new IllegalArgumentException("Must give size and species for enclosure.");
    }
    this.type = type;
    this.residents = new ArrayList<Monkey>();
  }

  
  @Override
  public void addMonkey(Monkey monkey) {
    if ("Isolation".equals(this.type)) {
      if (!this.isEmpty()) {
        throw new IllegalArgumentException("Not enough room.");
      }
      this.residents.add(monkey);
    } else {
      if (monkey.getSpecies() != this.species) {
        throw new IllegalArgumentException("Species not match.");
      }
      int monkeySize = monkey.getSizeInt();
      if (monkeySize <= this.remainSize) {
        this.residents.add(monkey);
        this.remainSize -= monkeySize;
      } else {
        throw new IllegalArgumentException("Not enough room.");
      }
    }
  }

  @Override
  public void removeMonkey(Monkey monkey) {
    if (!this.residents.contains(monkey)) {
      throw new IllegalArgumentException("This monkey is not here.");
    }
    this.remainSize += monkey.getSizeInt();
    this.residents.remove(monkey);
  }
  
  @Override
  public Monkey popMonkey() {
    if (this.isEmpty()) {
      throw new IllegalArgumentException("There is no monkey to pop.");
    }
    Monkey monkey = this.residents.get(this.residents.size() - 1);
    this.residents.remove(monkey);
    return monkey;
  }


  @Override
  public String[] showSign() {
    if ("Isolation".equals(this.type)) {
      throw new IllegalArgumentException("Not an enclosure.");
    }
    
    String[] sign = new String[this.residents.size()];
    int i = 0;
    for (Monkey monkey : this.residents) {
      String s = monkey.getName() + monkey.getSex() + monkey.getFood();
      sign[i] = s;
      i += 1;
    }
    return sign;
  }
  
  @Override
  public String getType() {
    return this.type;
  }
  
  @Override
  public int getSize() {
    if ("Isolation".equals(this.type)) {
      throw new IllegalArgumentException("Isolation has no size.");
    }
    return this.size;
  }

  @Override
  public int getRemainSize() {
    if ("Isolation".equals(this.type)) {
      throw new IllegalArgumentException("Isolation has no size.");
    }
    return this.remainSize;
  }
  
  @Override
  public Species getSpecies() {
    if ("Isolation".equals(this.type)) {
      throw new IllegalArgumentException("Isolation has no species.");
    }
    return this.species;
  }

  @Override
  public void setSpecies(Species species) {
    if ("Isolation".equals(this.type)) {
      throw new IllegalArgumentException("Isolation has no size.");
    }
    if (!this.isEmpty()) {
      throw new IllegalArgumentException("Enclosure must be empty.");
    }
    
    this.species = species;
  }
  
  @Override
  public List<Monkey> getResidents() {
    return this.residents;
  }
  
  @Override
  public boolean isEmpty() {
    return (this.residents == null) || (this.residents.size() == 0);
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += this.getType().hashCode();
    
    if ("Enclosure".equals(this.type)) {
      hash += this.getSize() + this.getRemainSize() + this.getSpecies().hashCode();;
    }
    
    for (Monkey monkey : this.residents) {
      hash += monkey.hashCode();
    }
    return hash;
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof JungleFriendsHousing)) {
      return false;
    }
    return this.hashCode() == o.hashCode();
  }
}

