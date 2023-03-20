package sanctuary;

import java.util.List;

/**
 * A house represents an isolation or an enclosure. Isolation only have type,
 * enclosure also have size and species.
 */
public interface Housing {

  /**
   * Add a monkey to this house.

   * @param monkey Instance of a monkey.
   * @throws IllegalArgumentException if monkey size is larger than remain room.
   */
  public void addMonkey(Monkey monkey);
  
  /**
   * Pop a monkey out of this house.

   * @param monkey Instance of a monkey.
   * @throws IllegalArgumentException is the monkey doesn't live here.
   */
  public void removeMonkey(Monkey monkey);
  
  /**
   * Pop the last monkey if there's any.

   * @return A Monkey object.
   */
  public Monkey popMonkey();
  
  /**
   * Produce a sign for a given enclosure that lists each individual monkey 
   * that is currently housed there. For each individual monkey, the sign 
   * should include their name, sex, and favorite food.

   * @return Strings of each monkey's name, sex and favorite food.
   * @throws IllegalArgumentException if it's not an enclosure.
   */
  public String[] showSign();
  
  /**
   * Get type of this house.

   * @return String, Isolation or Enclosure.
   */
  public String getType();
  
  /**
   * Get size of the enclosure.

   * @return size in int.
   * @throws IllegalArgumentException if it's an isolation.
   */
  public int getSize();
  
  /**
   * Get remain size of the enclosure.

   * @return remain size in int.
   * @throws IllegalArgumentException if it's an isolation.
   */
  public int getRemainSize();
  
  /**
   * Get species of the enclosure.

   * @return String of species.
   * @throws IllegalArgumentException if it's an isolation.
   */
  public Species getSpecies();

  /**
   * Reset species of the enclosure.

   * @param species The species of this house.
   * @throws IllegalArgumentException if it's an isolation.
   * @throws IllegalArgumentException if it's not empty.
   */
  public void setSpecies(Species species);
  
  /**
   * Residents in this house.

   * @return List of residents.
   */
  public List<Monkey> getResidents();
  
  /**
   * Determine if this house is empty.

   * @return True if it's empty. Otherwise false.
   */
  public boolean isEmpty();
}
