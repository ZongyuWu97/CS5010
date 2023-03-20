package sanctuary;

/**
 * Present a single monkey. Has information of name, species, sex, 
 * size, weight, favorite food, and age.

 * @author ZongyuWu
 *
 */
public class Monkey {

  private final String name; 
  private final Species species; 
  private final Sex sex; 
  private final Size size; 
  private final int weight; 
  private final Food food; 
  private final int age; 
  
  /**
   * Constructor of a monkey instance.

   * @param name Name of the monkey.
   * @param species Species of the monkey.
   * @param sex Sex of the monkey.
   * @param size Size of the monkey.
   * @param weight Weight of the monkey.
   * @param food Favorite food of the monkey.
   * @param age Age of the monkey.
   */
  public Monkey(String name, Species species, Sex sex, Size size,
      int weight, Food food, int age) {
    if (name.length() == 0) {
      throw new IllegalArgumentException("Name cannot be null.");
    }
    if (weight <= 0) {
      throw new IllegalArgumentException("Weight must be positive.");
    }
    if (age < 0) {
      throw new IllegalArgumentException("Age must be non-negative.");
    }

    
    this.name = name;
    this.species = species;
    this.sex = sex;
    this.size = size;
    this.weight = weight;
    this.food = food;
    this.age = age;
  }

  /**
   * Get its name.

   * @return the name.
   */
  public String getName() {
    return this.name;
  }

  /**
   * Get its species.

   * @return the species.
   */
  public Species getSpecies() {
    return this.species;
  }

  /**
   * Get its sex.

   * @return the sex.
   */
  public Sex getSex() {
    return this.sex;
  }

  /**
   * Get its size.

   * @return the size.
   */
  public Size getSize() {
    return this.size;
  }
  
  /**
   * Get the size in integer.

   * @return the size in int.
   */
  public int getSizeInt() {
    if (this.size == Size.Large) {
      return 10;
    }
    if (this.size == Size.Medium) {
      return 5;
    }
    if (this.size == Size.Small) {
      return 2;
    }
    
    return -1;
  }
  
  /**
   * Get its weight.

   * @return the weight.
   */
  public int getWeight() {
    return this.weight;
  }

  /**
   * Get its favorite food.

   * @return the food.
   */
  public Food getFood() {
    return this.food;
  }
  
  /**
   * Get the quantity of food needed according to its size.

   * @return Integer of quantity needed.
   */
  public int getFoodInt() {
    if (this.size == Size.Large) {
      return 500;
    }
    if (this.size == Size.Medium) {
      return 300;
    }
    if (this.size == Size.Small) {
      return 100;
    }  
    
    return -1;
  }


  /**
   * Get its age.

   * @return the age.
   */
  public int getAge() {
    return this.age;
  }
  
  @Override
  public int hashCode() {
    int hash = 0;
    hash += this.name.hashCode();
    hash += this.getSpecies().hashCode();
    hash += this.getSex().hashCode();
    hash += this.getSize().hashCode();
    hash += this.getWeight();
    hash += this.getAge();
    hash += this.getFood().hashCode();
    return hash;
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Monkey)) {
      return false;
    }
    return this.hashCode() == o.hashCode();
  }
}
